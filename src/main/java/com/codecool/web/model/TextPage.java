package com.codecool.web.model;

public class TextPage extends Page {

    private String title, content;

    public TextPage(int id, boolean published, String title, String content) {
        super(id, published);
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
