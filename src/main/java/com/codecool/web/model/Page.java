package com.codecool.web.model;

public abstract class Page {

    protected int id;
    protected boolean published;

    public Page(int id, boolean published) {
        this.id = id;
        this.published = false;
    }

    public int getId() {
        return id;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
