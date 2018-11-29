package ru.itmo.webmail.model.repository;

import ru.itmo.webmail.model.domain.Article;

public interface ArticleRepository {
    void save(Article article);
}
