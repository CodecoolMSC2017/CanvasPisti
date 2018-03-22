package com.codecool.web.model;

public abstract class Page {

    protected String title;
    protected boolean published;

    public Page(String title) {
        this.title = title;
        this.published = false;
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
}
