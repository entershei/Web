package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.News;
import ru.itmo.webmail.model.domain.NewsFront;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.service.NewsService;
import ru.itmo.webmail.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class Page {
    private UserService userService = new UserService();
    private NewsService newsService = new NewsService();

    void before(HttpServletRequest request, Map<String, Object> view) throws ServletException {
        view.put("userCount", userService.findCount());

        if (request.getSession().getAttribute("user") != null) {
            view.put("user", request.getSession().getAttribute("user"));
        }

        List<NewsFront> list = new ArrayList<>();
        for (News cur_news : newsService.findAll()) {
            list.add(new NewsFront(cur_news, getUserService()));
        }
        view.put("listNews", list);
    }

    void after(HttpServletRequest request, Map<String, Object> view) {
    }

    UserService getUserService() {
        return userService;
    }

    NewsService getNewsService() {
        return newsService;
    }
}
