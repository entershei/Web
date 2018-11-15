package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.repository.impl.NewsRepository;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

public class AddNewsPage extends Page {
    private void addNews(HttpServletRequest request, Map<String, Object> view) throws ServletException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            view.put("error", "Can't add news, because there haven't user");
            throw new RedirectException("/index", "canNotAddNews");
        }

        String text = request.getParameter("text");
        if (text != null) {
            Objects.requireNonNull(text);
            getNewsService().addNews(user.getId(), text);
            throw new RedirectException("/index", "newsDone");
        } else {
            throw new ServletException("News can't be empty");
        }
    }

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }
}
