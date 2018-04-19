package com.codecool.web.model;

public class AssignmentPage extends Page {

    private String question, answer;
    private int maxScore;
    private int actualScore;
    private int minimumScore;


    public AssignmentPage(String title,boolean ispublished, String question, String answer, int maxScore,int actualScore,int minimumScore) {
        super(title,ispublished);
        this.question = question;
        this.answer = answer;
        this.maxScore = maxScore;
        this.actualScore = actualScore;
        this.minimumScore = minimumScore;
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

    public int getActualScore() {
        return actualScore;
    }

    public void setActualScore(int actualScore) {
        this.actualScore = actualScore;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getMinimumScore() {
        return minimumScore;
    }
}
