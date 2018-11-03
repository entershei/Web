package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

class Page {
    private UserService userService = new UserService();

    void before(HttpServletRequest request, Map<String, Object> view) {
        view.put("userCount", userService.findCount());
    }

    void after(HttpServletRequest request, Map<String, Object> view) {
    }

    UserService getUserService() {
        return userService;
    }
}
