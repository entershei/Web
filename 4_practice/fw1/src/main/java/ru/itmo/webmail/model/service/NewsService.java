package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.domain.News;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.repository.impl.NewsRepository;

import java.util.List;

public class NewsService {
    private NewsRepository newsRepository = new NewsRepository();

    public void addNews(long userId, String newsText) {
        newsRepository.addNews(userId, newsText);
    }

    public List<News> findAll() {
        return newsRepository.findAll();
    }
}
