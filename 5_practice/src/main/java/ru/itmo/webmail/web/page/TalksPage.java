package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.Talk;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class TalksPage extends Page {
    private void sendMessageDone(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "You have sent message.");
    }

    private void sendMessage(HttpServletRequest request, Map<String, Object> view) {
        User user = getUser();

        if (user == null) {
            view.put("error", "Can't send message, because there haven't user.");
            throw new RedirectException("/index", "canNotSendMessage");
        }

        Talk talk = new Talk();
        talk.setSourceUserId(user.getId());

        String loginTargetUser = request.getParameter("loginTargetUser");
        String text = request.getParameter("text");

        try {
            getTalkService().validateSendMessage(loginTargetUser, text);

            User targetUser = getUserService().findByLogin(loginTargetUser);
            if (targetUser == null) {
                throw new ValidationException("Receiver doesn't exist.");
            }
        } catch (ValidationException e) {
            view.put("sourceUserId", talk.getSourceUserId());
            view.put("loginTargetUser", loginTargetUser);
            view.put("text", text);
            view.put("error", e.getMessage());
            return;
        }

        talk.setTargetUserId(getUserService().findByLogin(loginTargetUser).getId());

        getTalkService().sendMessage(talk, text);
        throw new RedirectException("/talks", "sendMessageDone");
    }

    private void findAllForUser(HttpServletRequest request, Map<String, Object> view) {
        User user = getUser();

        if (user == null) {
            view.put("error", "Сan't show message, because there haven't user.");
            throw new RedirectException("/index", "canNotSendMessage");
        }

        view.put("talks", getTalkService().findAllForUser(user.getId()));
        view.put("userCount", getTalkService().findCountForUser(user.getId()));
    }


    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    @Override
    public void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
        view.put("userService", getUserService());
        findAllForUser(request, view);
    }
}
