package com.codecool.web.dao;

import com.codecool.web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public List<User> findAll() throws SQLException;

    User findByEmail(String email)throws SQLException;

    User add(User user)throws SQLException;
}
