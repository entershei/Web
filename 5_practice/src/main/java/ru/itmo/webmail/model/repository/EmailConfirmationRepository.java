package ru.itmo.webmail.model.repository;

import ru.itmo.webmail.model.domain.EmailConfirmation;

import java.util.List;

public interface EmailConfirmationRepository {
    public List<EmailConfirmation> findAll();
    public EmailConfirmation findBySecret(String secret);
    public void save(long userId);
}
