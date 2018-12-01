package ru.itmo.wm4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wm4.form.NoticeCredentials;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class NoticePage extends Page {
    @GetMapping(path = "/notice")
    public String noticeGet(Model model) {
        model.addAttribute("noticeForm", new NoticeCredentials());
        return "NoticePage";
    }

    @PostMapping(path = "/notice")
    public String noticePost(@Valid @ModelAttribute("noticeForm") NoticeCredentials noticeForm,
                             BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "NoticePage";
        }

        return "redirect:/";
    }
}
