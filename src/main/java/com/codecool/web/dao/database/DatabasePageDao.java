package com.codecool.web.dao.database;

import com.codecool.web.dao.PageDao;
import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.Page;
import com.codecool.web.model.TextPage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabasePageDao extends AbstractDao  implements PageDao {
    public DatabasePageDao(Connection connection) {
        super(connection);
    }

    @Override
    public TextPage addTextPage(TextPage textPage) throws SQLException {
        if (textPage.getContent().equals(null) || "".equals(textPage.getTitle())) {
            throw new IllegalArgumentException("Nem fasza báttya");
        }
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO text_page (title,content) VALUES (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, textPage.getTitle());
            statement.setString(2, textPage.getContent());
            executeInsert(statement);
            return new TextPage(textPage.getTitle(), textPage.getContent());
        }catch (SQLException ex) {
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
        String sql = "INSERT INTO assignment_page (title,question,answer,max_score) VALUES (?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, assignmentPage.getTitle());
            statement.setString(2, assignmentPage.getQuestion());
            statement.setString(3, assignmentPage.getAnswer());
            statement.setInt(4,assignmentPage.getMaxScore());

            executeInsert(statement);
            return new AssignmentPage(assignmentPage.getTitle(), assignmentPage.getQuestion(),assignmentPage.getAnswer(),assignmentPage.getMaxScore());
        }catch (SQLException ex) {
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
       ResultSet resultSet = statement.executeQuery(sql)){
           List<Page> allPages = new ArrayList<>();
           while (resultSet.next()){
               ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
               int numberOfColumns = resultSetMetaData.getColumnCount();
               for (int i = 0; i < numberOfColumns; i++) {
                   String columName = resultSetMetaData.getColumnName(i);
                   if("max_score".equals(columName)){
                       allPages.add(fetchAssPage(resultSet));
                   }else if("content".equals(columName)){
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
        try(Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next())
                size = fetchListSize(resultSet);
        }
        return size;
    }


    private TextPage fetchTextPage(ResultSet resultSet) throws SQLException {
        String title =  resultSet.getString("title");
        String content =  resultSet.getString("content");
        return new TextPage(title,content);
    }

    private AssignmentPage fetchAssPage(ResultSet resultSet) throws SQLException {
        String title = resultSet.getString("title");
        String question = resultSet.getString("question");
        String answer = resultSet.getString("answer");
        int maxscore = resultSet.getInt("max_score");
       return new AssignmentPage(title,question,answer,maxscore);
    }

    private int fetchListSize(ResultSet resultSet)throws SQLException{
        int listSize = resultSet.getInt("count");
        return listSize;
    }

}