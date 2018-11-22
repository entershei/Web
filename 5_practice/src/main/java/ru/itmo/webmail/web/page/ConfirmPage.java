package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.EmailConfirmation;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ConfirmPage extends Page {
    private void confirm(HttpServletRequest request, Map<String, Object> view) {
        String secret = request.getParameter("secret");
        if (secret == null) {
            view.put("message", "No Confirmation Secret");
            return;
        }

        EmailConfirmation emailConfirmation = getEmailConfirmationService().findSecret(secret);
        if (emailConfirmation == null) {
            view.put("message", "Incorrect Confirmation Secret");
            return;
        }

        getUserService().confirmById(emailConfirmation.getUserId());
        view.put("message", "You have been verified.");
    }

    private void action(HttpServletRequest request, Map<String, Object> view) {
        confirm(request, view);
    }
}
