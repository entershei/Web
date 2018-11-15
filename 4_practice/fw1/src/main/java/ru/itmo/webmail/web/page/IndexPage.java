package ru.itmo.webmail.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class IndexPage extends Page {
    private void action() {
        // No operations.
    }

    private void registrationDone(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "You have been registered");
    }

    private void canNotAddNews(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "Can't add news, because there haven't user");
    }

    private void enterDone(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "You have been enter");
    }

    private void logoutDone(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "You have been log out");
    }

    private void newsDone(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "You have been successfully sent the news");
    }
}
