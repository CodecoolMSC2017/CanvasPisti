package com.codecool.web.model;

public class TextPage extends Page {

    private String content;

    public TextPage(String title,boolean ispublished, String content) {
        super(title,ispublished);
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
