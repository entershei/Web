package ru.itmo.wm4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wm4.service.UserService;

@Controller
public class PersonalInformationPage extends Page {
    private final UserService userService;

    public PersonalInformationPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "user/{id}")

    public String getPersonalInfo(@PathVariable long id, Model model)  {
        model.addAttribute("user_information", userService.findById(id));
        return "PersonalInfoPage";
    }
}
