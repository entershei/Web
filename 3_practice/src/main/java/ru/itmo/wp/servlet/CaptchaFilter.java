package ru.itmo.wp.servlet;

import ru.itmo.wp.util.ImageUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;

public class CaptchaFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (skip_request(request)) {
            chain.doFilter(request, response);
            return;
        }

        String captchaNumber = String.valueOf(ThreadLocalRandom.current().nextInt(100, 1000));
        request.getSession().setAttribute("Correct_answer", captchaNumber);

        response.setContentType("text/html");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.print(captchaImage(captchaNumber));
        Path captchaFormPath = Paths.get(getServletContext().getRealPath("static"), "captcha.html");
        Files.copy(captchaFormPath, outputStream);
        outputStream.flush();
    }

    private boolean skip_request(HttpServletRequest request) {
        return  (!GETRequest(request) || passedCaptcha(request) || correctAnswer(request));
    }

    private boolean GETRequest(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("GET");
    }

    private boolean passedCaptcha(HttpServletRequest request) {
        if (request.getSession().getAttribute("Captcha_passed") != null) {
            return (Boolean) request.getSession().getAttribute("Captcha_passed");
        }
        return false;
    }

    private boolean correctAnswer(HttpServletRequest request) {
        String answer = request.getParameter("answer");
        if (answer != null) {
            if (answer.equals(request.getSession().getAttribute("Correct_answer"))) {
                request.getSession().setAttribute("Captcha_passed", true);
                return true;
            }
        }
        return false;
    }

    private String captchaImage(String captchaNumber) {
        byte[] imageBytes = ImageUtils.toPng(captchaNumber);
        String base64String = Base64.getEncoder().encodeToString(imageBytes);
        return String.format("<img src=\"data:image/png;base64,%s\">\n", base64String);
    }
}
