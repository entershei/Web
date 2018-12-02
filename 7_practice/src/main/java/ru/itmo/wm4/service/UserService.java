package ru.itmo.wm4.service;

import org.springframework.stereotype.Service;
import ru.itmo.wm4.domain.User;
import ru.itmo.wm4.form.ChangeDisableCredentials;
import ru.itmo.wm4.form.UserCredentials;
import ru.itmo.wm4.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isLoginVacant(String login) {
        return userRepository.countByLogin(login) == 0;
    }

    public User register(UserCredentials registerForm) {
        User user = new User();
        user.setLogin(registerForm.getLogin());
        userRepository.save(user);
        userRepository.updatePasswordSha(user.getId(), registerForm.getPassword());
        return user;
    }

    public User findById(Long userId) {
        return userId == null ? null : userRepository.findById(userId).orElse(null);
    }

    public User findByLoginAndPassword(String login, String password) {
        return login == null || password == null ? null : userRepository.findByLoginAndPassword(login, password);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void changeDisable(ChangeDisableCredentials changeDisableForm) {
        User user = userRepository.findById(changeDisableForm.getUserId()).orElse(null);

        if (user == null) {
            return;
        }

        if (user.getDisabled()) {
            userRepository.updateDisabled(user.getId(), false);
        } else {
            userRepository.updateDisabled(user.getId(), true);
        }
    }
}
