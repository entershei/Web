package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.service.UserService;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class LogoutPage extends Page {
    private void action(HttpServletRequest request) {
        UserService.logout(request);
        throw new RedirectException("/index", "logoutDone");
    }
}
