package com.codecool.web.model;

public abstract class Page {

    protected String title;
    protected boolean published;
    protected int id;

    public Page(String title) {
        this.title = title;
        this.published = false;
        id=0;
    }

    public String getTitle() {
        return title;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
