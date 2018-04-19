package com.codecool.web.model;

public class Grade {
    private String email;
    private String title;
    private int actScore;
    private int maxScore;

    public Grade(String email, String title, int actScore, int maxScore) {
        this.email = email;
        this.title = title;
        this.actScore = actScore;
        this.maxScore = maxScore;
    }

    public String getEmail() {
        return email;
    }

    public String getTitle() {
        return title;
    }

    public int getActScore() {
        return actScore;
    }

    public int getMaxScore() {
        return maxScore;
    }
}
