package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.domain.Talk;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.repository.TalksRepository;
import ru.itmo.webmail.model.repository.impl.TalksRepositoryImpl;
import ru.itmo.webmail.web.exception.RedirectException;

import java.util.List;

public class TalksService {
    private TalksRepository talkRepository = new TalksRepositoryImpl();

    public List<Talk> findAllForUser(long userId) {
        return talkRepository.findAllForUser(userId);
    }

    public long findCountForUser(long userId) {
        return talkRepository.findAllForUser(userId).size();
    }

    public void sendMessage(Talk talk, String text) {
        talkRepository.save(talk, text);
    }

    public void validateSendMessage(String loginTargetUser, String text) throws ValidationException {
        if (loginTargetUser == null || loginTargetUser.length() == 0) {
            throw new ValidationException("Recipient login must not be empty");
        }

        if (text == null || text.length() == 0) {
            throw new ValidationException("Text must not be empty");
        }

        if (text.length() > 500) {
            throw new ValidationException("Text length must be less than 500.");
        }
    }
}
