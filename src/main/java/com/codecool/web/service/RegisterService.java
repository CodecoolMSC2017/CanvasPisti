package com.codecool.web.service;

import com.codecool.web.model.User;

import java.util.ArrayList;
import java.util.List;

public final class RegisterService {

    public User getReg(){return new User("You successfully registered");}

    public User getUnReg(){return new User("You need to fill out everything!");}

    private List<User> userList = new ArrayList<>();

    public List<User> getUserList() {
        return userList;
    }

}
