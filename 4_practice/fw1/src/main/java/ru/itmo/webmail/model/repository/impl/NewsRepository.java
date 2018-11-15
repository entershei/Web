package ru.itmo.webmail.model.repository.impl;

import ru.itmo.webmail.model.domain.News;
import ru.itmo.webmail.model.domain.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NewsRepository {
    private static final File tmpDir = new File(System.getProperty("java.io.tmpdir"));

    private List<News> listNews;

    public NewsRepository() {
        try {
            //noinspection unchecked
            listNews = (List<News>) new ObjectInputStream(
                    new FileInputStream(new File(tmpDir, getClass().getSimpleName()))).readObject();
        } catch (Exception e) {
            listNews = new ArrayList<>();
        }
    }

    public void addNews(long userId, String newsText) {
        News news = new News(userId, newsText);
        listNews.add(news);

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(new File(tmpDir, getClass().getSimpleName())));
            objectOutputStream.writeObject(listNews);
            objectOutputStream.close();
        } catch (Exception e) {
            throw new RuntimeException("Can't save news", e);
        }
    }

    public List<News> findAll() {
        return listNews;
    }
}
