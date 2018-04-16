package com.codecool.web.service;

import com.codecool.web.model.User;
import com.codecool.web.service.exceptions.ServiceException;

import java.sql.SQLException;

public interface LoginserviceInt {

    User loginUser(String email)throws SQLException,ServiceException;
}
