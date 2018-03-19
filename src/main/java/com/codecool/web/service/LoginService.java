package com.codecool.web.service;

import com.codecool.web.model.User;

public final class LoginService {

    public User getLog(){
        return new User("Logged in!");
    }

    public User getUnLog(){
        return new User("Please give correct inputs!");
    }
}
