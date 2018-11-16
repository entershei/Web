package ru.itmo.webmail.model.domain;

import java.util.Date;

public class EmailConfirmation {
    private long id;
    private long userId;
    private String secret;
    private Date creationTime;

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getSecret() {
        return secret;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
