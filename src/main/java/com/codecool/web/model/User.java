package com.codecool.web.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User {
    private String name;
    private String email;
    private String role;
    private Map<String,Boolean> userAttendanceMap;

    public User(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Map<String, Boolean> getUserAttendanceMap() {
        return userAttendanceMap;
    }

}
