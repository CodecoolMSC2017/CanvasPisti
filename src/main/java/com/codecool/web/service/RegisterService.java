package com.codecool.web.service;

import com.codecool.web.model.User;

import java.util.ArrayList;
import java.util.List;

public final class RegisterService {

    public User getReg(){return new User("You successfully registered");}

    public User getAllReg(){return new User("That person already registered");}

    private List<User> userList = new ArrayList<>();

    public List<User> getUserList() {
        return userList;
    }

}
