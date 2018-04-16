package com.codecool.web.service.simple;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.LoginserviceInt;
import com.codecool.web.service.exceptions.ServiceException;

import java.sql.SQLException;

public final class SimpleLoginService implements LoginserviceInt {
    private final UserDao userDao;

    public SimpleLoginService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User loginUser(String email) throws SQLException, ServiceException {
        User user = userDao.findByEmail(email);
        if (user == null || !user.getEmail().equals(email)) {
            throw new ServiceException("Bad login");
        }
        return user;
    }
}
