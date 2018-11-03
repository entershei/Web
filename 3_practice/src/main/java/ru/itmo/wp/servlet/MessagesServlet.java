package ru.itmo.wp.servlet;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessagesServlet extends HttpServlet {
    private List<Message> messages = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getPathInfo()) {
            case "/auth": {
                String user = Optional.ofNullable(request.getParameter("user"))
                        .orElse("");

                if (!user.equals("")) {
                    request.getSession().setAttribute("user", user);
                }

                String json = new Gson().toJson(user);
                response.getWriter().print(json);
                response.getWriter().flush();
                break;
            }
            case "/findAll": {
                String json = new Gson().toJson(messages.toArray());
                response.getWriter().print(json);
                response.getWriter().flush();
                break;
            }
            case "/add":
                messages.add(new Message((String) request.getSession().getAttribute("user"), request.getParameter("text")));
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}

class Message {
    String user;
    String text;

    Message(String user_, String text_) {
        user = user_;
        text = text_;
    }
}