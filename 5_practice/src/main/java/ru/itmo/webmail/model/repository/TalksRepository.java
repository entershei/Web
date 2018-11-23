package ru.itmo.webmail.model.repository;

import ru.itmo.webmail.model.domain.Talk;

import java.util.List;

public interface TalksRepository {
    void save(Talk talk, String text);
    List<Talk> findAllForUser(long userId);
}
