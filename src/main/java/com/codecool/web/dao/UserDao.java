package com.codecool.web.dao;

import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    List<User> findAll() throws SQLException;

    User findByEmail(String email)throws SQLException;

    User add(User user)throws SQLException;

    void changeName(String name, String email)throws SQLException;

    void changeRole(String role, String email)throws SQLException;


    void checkAttendance(String date, String email)throws SQLException;

    void addSubmission(User user , AssignmentPage assPage)throws SQLException;

    List<User> listAttendance(String date) throws SQLException;

    List<String> listAttDates() throws SQLException;

    void deleteFromAttendance(String email, String date) throws SQLException;
}
