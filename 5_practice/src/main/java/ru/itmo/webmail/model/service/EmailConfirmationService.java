package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.domain.EmailConfirmation;
import ru.itmo.webmail.model.repository.EmailConfirmationRepository;
import ru.itmo.webmail.model.repository.impl.EmailConfirmationRepositoryImpl;

public class EmailConfirmationService {
    private EmailConfirmationRepository emailConfirmationRepository = new EmailConfirmationRepositoryImpl();

    public EmailConfirmationRepository getUserRepository() {
        return emailConfirmationRepository;
    }

    public EmailConfirmation findSecret(String secret) {
        return emailConfirmationRepository.findBySecret(secret);
    }

    public void save(long userId) {
        emailConfirmationRepository.save(userId);
    }
}
