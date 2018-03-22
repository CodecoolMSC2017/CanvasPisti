package com.codecool.web.model;

public class AssignmentPage extends Page {

    private String question, answer;
    private int maxScore;

    public AssignmentPage(String title, String question, String answer, int maxScore) {
        super(title);
        this.question = question;
        this.answer = answer;
        this.maxScore = maxScore;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
