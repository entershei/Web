package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.domain.Article;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.repository.ArticleRepository;
import ru.itmo.webmail.model.repository.impl.ArticleRepositoryImpl;

public class ArticleService {
    private ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public void validateSendArticle(String title, String text) throws ValidationException {
        if (title == null || title.isEmpty()) {
            throw new ValidationException("Title is required");
        }

        if (title.length() > 255) {
            throw new ValidationException("Title can't be longer than 255");
        }

        if (text == null || text.isEmpty()) {
            throw new ValidationException("Text is required");
        }

        if (text.length() > 500) {
            throw new ValidationException("Text can't be longer than 500");
        }
    }

    public void sendArticle(Article article) {
        articleRepository.save(article);
    }
}
