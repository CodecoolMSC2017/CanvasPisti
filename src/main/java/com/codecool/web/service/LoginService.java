package com.codecool.web.service;

import com.codecool.web.model.User;

public final class LoginService {
    static User user;

    public String getLog(){
        return "Logged in!";
    }

    public String getUnLog(){
        return "Please give correct inputs!";
    }

    public static User getCurrentUser(){return user;}

    public static void setCurrentUser(User user1){user = user1;}
}
