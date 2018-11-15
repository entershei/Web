package ru.itmo.webmail.model.domain;

import ru.itmo.webmail.model.service.UserService;

import javax.servlet.ServletException;
import java.util.Optional;

public class NewsFront {
        private String login;
        private String text;

        public NewsFront(News news, UserService userService) throws ServletException {
            this.text = news.getText();
            Optional<User> user = userService.findAll().stream()
                    .filter(u -> u.getId() == news.getUserId())
                    .findFirst();
            if (!user.isPresent()) {
                throw new ServletException("Unknown user id");
            }
            this.login = user.get().getLogin();
        }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}