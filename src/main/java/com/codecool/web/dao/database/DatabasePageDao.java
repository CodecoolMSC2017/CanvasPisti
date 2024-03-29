package com.codecool.web.dao.database;

import com.codecool.web.dao.PageDao;
import com.codecool.web.model.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabasePageDao extends AbstractDao implements PageDao {
    public DatabasePageDao(Connection connection) {
        super(connection);
    }

    @Override
    public ArrayList<Grade> listAllGrades(String email) throws SQLException {
        String sql = "Select * from user_ass where student_email = ?";
        ArrayList<Grade> myList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    myList.add(fetchGrade(resultSet));
                }
            }
        }
        System.out.println(myList.size() + "listallgRadeds");
        return myList;
    }

    @Override
    public TextPage addTextPage(TextPage textPage) throws SQLException {
        if (textPage.getContent().equals(null) || "".equals(textPage.getTitle())) {
            throw new IllegalArgumentException("Nem fasza báttya");
        }
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO text_page (title,is_published,content) VALUES (?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, textPage.getTitle());
            statement.setBoolean(2, textPage.isPublished());
            statement.setString(3, textPage.getContent());
            executeInsert(statement);
            return new TextPage(textPage.getTitle(), textPage.isPublished(), textPage.getContent());
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    @Override
    public AssignmentPage addAssignment(AssignmentPage assignmentPage) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO assignment_page (title,is_published,question,answer,max_score) VALUES (?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, assignmentPage.getTitle());
            statement.setBoolean(2, assignmentPage.isPublished());
            statement.setString(3, assignmentPage.getQuestion());
            statement.setString(4, assignmentPage.getAnswer());
            statement.setInt(5, assignmentPage.getMaxScore());

            executeInsert(statement);
            return new AssignmentPage(assignmentPage.getTitle(), assignmentPage.isPublished(), assignmentPage.getQuestion(), assignmentPage.getAnswer(), assignmentPage.getMaxScore(), assignmentPage.getActualScore(), assignmentPage.getMinimumScore());
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    @Override
    public Page add(Page page) {
        return null;
    }

    @Override
    public List<Page> listAllText() throws SQLException {
        String sql = "SELECT * FROM text_page";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Page> textPages = new ArrayList<>();
            while (resultSet.next()) {
                textPages.add(fetchTextPage(resultSet));
            }
            return textPages;
        }
    }

    @Override
    public List<Page> listAllAss() throws SQLException {
        String sql = "SELECT * FROM assignment_page";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Page> assPage = new ArrayList<>();
            while (resultSet.next()) {
                assPage.add(fetchAssPage(resultSet));
            }
            return assPage;
        }
    }

    @Override
    public List<Page> listAllPages() throws SQLException {
        String sql = "Select * from text_page\n" +
                "Full join assignment_page on text_page.title = assignment_page.title;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Page> allPages = new ArrayList<>();
            while (resultSet.next()) {
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int numberOfColumns = resultSetMetaData.getColumnCount();
                for (int i = 0; i < numberOfColumns; i++) {
                    String columName = resultSetMetaData.getColumnName(i);
                    if ("max_score".equals(columName)) {
                        allPages.add(fetchAssPage(resultSet));
                    } else if ("content".equals(columName)) {
                        allPages.add(fetchTextPage(resultSet));
                    }

                }

            }
            return allPages;

        }

    }

    @Override
    public int getListSize() throws SQLException {
        int size = 0;
        String sql = "select count('title') from text_page\n" +
                "Full join assignment_page on text_page.title = assignment_page.title\n" +
                "\n";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next())
                size = fetchListSize(resultSet);
        }
        return size;
    }


    private TextPage fetchTextPage(ResultSet resultSet) throws SQLException {
        String title = resultSet.getString("title");
        boolean ispublished = resultSet.getBoolean("is_published");

        String content = resultSet.getString("content");
        return new TextPage(title, ispublished, content);
    }

    private AssignmentPage fetchAssPage(ResultSet resultSet) throws SQLException {
        String title = resultSet.getString("title");
        boolean ispublished = resultSet.getBoolean("is_published");
        String question = resultSet.getString("question");
        String answer = resultSet.getString("answer");
        int maxscore = resultSet.getInt("max_score");
        return new AssignmentPage(title, ispublished, question, answer, maxscore, 0, 0);
    }

    private int fetchListSize(ResultSet resultSet) throws SQLException {
        int listSize = resultSet.getInt("count");
        return listSize;
    }

    private Grade fetchGrade(ResultSet resultSet) throws SQLException {

            String email = resultSet.getString("student_email");
            String title = resultSet.getString("assignment_title");
            int actScore = resultSet.getInt("actual_score");
            int maxScore = resultSet.getInt("max_score");
            Grade grade1 = new Grade(email, title, actScore, maxScore);
            return grade1;



    }

    @Override
    public void publishTask(ArrayList<Page> allpages, String[] arr) throws SQLException {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < allpages.size(); j++) {

                    if (arr[i].equals(allpages.get(j).getTitle())) {
                        if (allpages.get(j) instanceof TextPage) {
                            System.out.println(allpages.get(j).isPublished() + "Shit");
                            if (!allpages.get(j).isPublished()) {
                                setTextPublished(true, allpages.get(j).getTitle());
                            } else {
                                setTextPublished(false, allpages.get(j).getTitle());
                            }
                        } else if (allpages.get(j) instanceof AssignmentPage) {
                            if (!allpages.get(j).isPublished()) {
                                setAssPublished(true, allpages.get(j).getTitle());
                            } else {
                                setAssPublished(false, allpages.get(j).getTitle());
                            }
                        }
                    }
                }
            }
        }

    }


    public void setTextPublished(boolean published, String title) throws SQLException {
        String sql = "UPDATE text_page SET is_published = ? where title = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, published);
            statement.setString(2, title);
            statement.executeUpdate();
        }

    }

    public void setAssPublished(boolean published, String title) throws SQLException {
        String sql = "UPDATE assignment_page SET is_published = ? where title = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, published);
            statement.setString(2, title);
            statement.executeUpdate();
        }

    }


    @Override
    public void findAllAssignmentByUser(String email) throws SQLException {
        Map<User, ArrayList<AssignmentPage>> submissionList = new HashMap<>();
        List<User> usrList = new ArrayList<>();
        String sql = "SELECT * from user_ass\n" +
                "left join assignment_page on assignment_page.title = user_ass.student_email WHERE student_email = 'user1@user1'";

    }

    public Page findByAssignmentTitle(String title) throws SQLException {
        if (title == null || "".equals(title)) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        String sql = "SELECT * FROM assignment_page WHERE title = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchAssPage(resultSet);
                }
            }
        }
        return null;
    }

    public HashMap<User, ArrayList<AssignmentPage>> getSubmissionList() throws SQLException {
        String sql = "select * from user_ass";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchAssigmentHashMap(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public void updateAssignemnt(int score, String email, String title) throws SQLException {
        String sql = "UPDATE user_ass SET actual_score = ? WHERE student_email = ? and assignment_title = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, score);
            statement.setString(2, email);
            statement.setString(3, title);
            statement.executeUpdate();
        }
    }

    private HashMap<User, ArrayList<AssignmentPage>> fetchAssigmentHashMap(ResultSet resultSet) throws SQLException {
        HashMap<User, ArrayList<AssignmentPage>> tmpHashMap = new HashMap();
        while (resultSet.next()) {
            String name = resultSet.getString(1);
            String email = resultSet.getString(2);
            String role = resultSet.getString(3);
            String pageTitle = resultSet.getString(4);
            boolean is_published = resultSet.getBoolean(5);
            String question = resultSet.getString(6);
            String answer = resultSet.getString(7);
            int max_score = resultSet.getInt(8);
            int actual_score = resultSet.getInt(9);
            int minimum_score = resultSet.getInt(10);
            AssignmentPage page1 = new AssignmentPage(pageTitle, is_published, question, answer, max_score, actual_score, minimum_score);
            User tmpuser = new User(name, email, role);
            if (tmpHashMap.containsKey(tmpuser)) {
                ArrayList<AssignmentPage> pagez = tmpHashMap.get(tmpuser);
                if (!pagez.contains(page1))
                    pagez.add(page1);

            } else {
                ArrayList<AssignmentPage> assList = new ArrayList<>();
                assList.add(page1);
                tmpHashMap.put(tmpuser, assList);

            }
        }
        return tmpHashMap;
    }
}
