package ru.itmo.webmail.model.domain;

import javax.xml.crypto.Data;

public class Article {
    private long id;
    private long userId;
    private String title;
    private String text;
    private Data creationTime;

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreationTime(Data creationTime) {
        this.creationTime = creationTime;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Data getCreationTime() {
        return creationTime;
    }
}
