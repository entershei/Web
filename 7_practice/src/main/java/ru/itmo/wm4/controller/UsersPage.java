package ru.itmo.wm4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wm4.form.ChangeDisableCredentials;
import ru.itmo.wm4.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public String main(Model model) {
        model.addAttribute("users", userService.findAll());
        return "UsersPage";
    }

    @GetMapping(path = "/changeDisable")
    public String changeDisabledGet(Model model) {
        model.addAttribute("changeDisableForm", new ChangeDisableCredentials());
        return "UsersPage";
    }

    @PostMapping(path = "/changeDisable")
    public String changeDisabledPost(@Valid @ModelAttribute("changeDisableForm") ChangeDisableCredentials changeDisableForm,
                              BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "UsersPage";
        }

        userService.changeDisable(changeDisableForm);

        return "redirect:/users";
    }
}
