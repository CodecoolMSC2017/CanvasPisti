package com.codecool.web.service;

import com.codecool.web.model.User;
import com.codecool.web.service.exceptions.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RegisterServiceInt {
    User addUser(User user) throws ServiceException,SQLException;
}
