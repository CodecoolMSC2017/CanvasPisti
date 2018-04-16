package com.codecool.web.service.simple;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.RegisterServiceInt;
import com.codecool.web.service.exceptions.ServiceException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public  class SimpleRegisterService   implements RegisterServiceInt {
    private final UserDao userDao;

    public SimpleRegisterService(UserDao userDao){
        this.userDao = userDao;
    }
    @Override
    public User addUser(User user) throws ServiceException,SQLException {
        try {
            return userDao.add(user);
        }catch (IllegalArgumentException ex){
            throw new ServiceException(ex.getMessage());
        }
    }
}
