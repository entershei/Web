package ru.itmo.webmail.model.service;

import com.google.common.hash.Hashing;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.repository.UserRepository;
import ru.itmo.webmail.model.repository.impl.UserRepositoryImpl;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class UserService {
    private long lastId = -1;

    private static final String USER_PASSWORD_SALT = "dc3475f2b301851b";

    private UserRepository userRepository = new UserRepositoryImpl();

    public void validateRegistration(User user, String password, String passwordConfirmation, String email)
                                                                            throws ValidationException {
        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            throw new ValidationException("Login is required");
        }
        if (!user.getLogin().matches("[a-z]+")) {
            throw new ValidationException("Login can contain only lowercase Latin letters");
        }
        if (user.getLogin().length() > 8) {
            throw new ValidationException("Login can't be longer than 8");
        }
        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new ValidationException("Login is already in use");
        }

        if (password == null || password.isEmpty()) {
            throw new ValidationException("Password is required");
        }
        if (password.length() < 4) {
            throw new ValidationException("Password can't be shorter than 4");
        }
        if (password.length() > 32) {
            throw new ValidationException("Password can't be longer than 32");
        }

        if (passwordConfirmation == null || passwordConfirmation.isEmpty()) {
            throw new ValidationException("Password Confirmation is required");
        }
        if (!password.equals(passwordConfirmation)) {
            throw new ValidationException("Password and Password Confirmation must match");
        }

        if  (email == null || email.isEmpty()) {
            throw new ValidationException("Email Confirmation is required");
        }
        if (!email.matches("^[a-z]+@[a-z]+\\.[a-z]+$")) {
            throw new ValidationException("Email is incorrect");
        }
        if (findAll().stream().anyMatch(u -> email.equals(u.getEmail()))) {
            throw new ValidationException("This email is already registered. Please use another one");
        }
    }

    public void register(User user, String password, String email) {
        user.setPasswordSha1(Hashing.sha256().hashString(USER_PASSWORD_SALT + password,
                StandardCharsets.UTF_8).toString());
        user.setId(newId());
        user.setEmail(email);
        userRepository.save(user);
    }

    private long newId() {
        if (lastId == -1) {
            lastId = findLastId();
        }

        ++lastId;
        return lastId;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    private long findLastId() {
        return userRepository.findLastId();
    }

    public long findCount() { return userRepository.findCount(); }
}
