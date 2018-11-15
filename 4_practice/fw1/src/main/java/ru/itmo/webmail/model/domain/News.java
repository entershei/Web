package ru.itmo.webmail.model.domain;

import java.io.Serializable;

public class News implements Serializable {
    private long userId;
    private String text;

    public long getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public News(long userID, String newsText) {
        userId = userID;
        text = newsText;
    }
}
