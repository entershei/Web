package ru.itmo.webmail.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class IndexPage extends Page {
    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    private void registrationDone(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "You have been registered. Please, confirm your email.");
    }

    private void cannotregister(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "This email is already taken");
    }

    private void canNotSendMessage(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "Can't send message, because there haven't user.");
    }
}
