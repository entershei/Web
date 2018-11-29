package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.Article;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ArticlePage extends Page {
    private Map<String, Object> sendArticle(HttpServletRequest request, Map<String, Object> view) {
        User user = getUser();

        if (user == null) {
            view.put("error", "Can't send article, because there haven't user.");
            return view;
        }

        Article article = new Article();
        article.setUserId(user.getId());

        String title = request.getParameter("title");
        String text = request.getParameter("text");

        try {
            getArticleService().validateSendArticle(title, text);
        } catch (ValidationException e) {
            view.put("success", false);
            view.put("error", e.getMessage());
            return view;
        }

        article.setTitle(title);
        article.setText(text);

        getArticleService().sendArticle(article);
        view.put("success", true);

        return view;
    }

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }
}
