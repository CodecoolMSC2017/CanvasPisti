package com.codecool.web.dao.database;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DatabaseUserDao extends AbstractDao implements UserDao {
    public DatabaseUserDao(Connection connection) {
        super(connection);
    }

    public List<User> findAll() throws SQLException {
        String sql = "SELECT * FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(fetchUser(resultSet));
            }
            return users;
        }
    }

    private User fetchUser(ResultSet resultSet) throws SQLException {
        String email = resultSet.getString("email");
        String name = resultSet.getString("name");
        String role = resultSet.getString("role");
        return new User(name, email, role);
    }


    @Override
    public User findByEmail(String email) throws SQLException {
        if (email == null || "".equals(email)) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        String sql = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchUser(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public User add(User user) throws SQLException {
        if (user.getEmail() == null || "".equals(user.getEmail()) || user.getName() == null || "".equals(user.getName()) || user.getRole() == null || "".equals(user.getRole())) {
            throw new IllegalArgumentException("Nem good");
        }
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO users (email,name,role) VALUES (?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getName());
            statement.setString(3, user.getRole());
            executeInsert(statement);

            return new User(user.getEmail(), user.getName(), user.getRole());
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    @Override
    public void changeName(String name, String email) throws SQLException {
        String sql = "UPDATE users SET name = ? WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void changeRole(String role, String email) throws SQLException {
        String sql = "UPDATE users SET role = ? WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, role);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void checkAttendance(String date, String email) throws SQLException {
        String sql = "INSERT INTO attendance (att_date,att_email) VALUES (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, date);
            statement.setString(2, email);
            executeInsert(statement);

        } catch (SQLException ex) {


        }

    }

    public void addSubmission (User user, AssignmentPage assPage) throws SQLException {
        String sql = "Insert into user_ass (name,student_email,role,assignment_title,is_published,question,answer,max_score,actual_score,minimum_score) values (?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getRole());
            statement.setString(4, assPage.getTitle());
            statement.setBoolean(5, assPage.isPublished());
            statement.setString(6, assPage.getQuestion());
            statement.setString(7, assPage.getAnswer());
            statement.setInt(8, assPage.getMaxScore());
            statement.setInt(9, assPage.getActualScore());
            statement.setInt(10, assPage.getMinimumScore());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public List<User> listAttendance(String date) throws SQLException {
        String sql = "SELECT name, email, role FROM users INNER JOIN attendance ON attendance.att_email = users.email WHERE att_date = ?";
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, date);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(fetchUser(resultSet));
                }
                return users;
            }
        }
    }

    public List<String> listAttDates() throws SQLException {
        String sql = "SELECT att_date FROM attendance GROUP BY att_date";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<String> dates = new ArrayList<>();
            while (resultSet.next()) {
                String datesStr = resultSet.getString("att_date");
                dates.add(datesStr);
            }
            return dates;
        }
    }

    public void deleteFromAttendance(String email, String date) throws SQLException {
        String sql = "DELETE FROM attendance WHERE att_email = ? AND att_date = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,email);
            statement.setString(2, date);
            statement.executeUpdate();
        }
    }
}