package ru.itmo.wp.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        String[] uris = uri.split("\\+");

        response.setContentType(getContentTypeFromName(uris[0]));

        for (String cur_uri : uris) {
            add_uri(cur_uri, response);
        }

        response.getOutputStream().flush();
    }

    private void add_uri(String uri, HttpServletResponse response) throws IOException {
        File file = new File((Paths.get("src/main/webapp/static", uri)).toString());
        if (!file.exists()) {
            file = Paths.get(getServletContext().getRealPath("static"), uri).toFile();
        }

        if (file.isFile()) {
            Files.copy(file.toPath(), response.getOutputStream());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private String getContentTypeFromName(String name) {
        name = name.toLowerCase();

        if (name.endsWith(".png")) {
            return "image/png";
        }

        if (name.endsWith(".jpg")) {
            return "image/jpeg";
        }

        if (name.endsWith(".html")) {
            return "text/html";
        }

        if (name.endsWith(".css")) {
            return "text/css";
        }

        if (name.endsWith(".js")) {
            return "application/javascript";
        }

        throw new IllegalArgumentException("Can't find content type for '" + name + "'.");
    }
}
