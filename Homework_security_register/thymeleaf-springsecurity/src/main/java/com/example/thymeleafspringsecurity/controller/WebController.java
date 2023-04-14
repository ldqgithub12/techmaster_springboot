package com.example.thymeleafspringsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WebController {
    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("name", "Bùi Hiên");

        List<String> users = List.of("Nguyễn Văn A", "Trần Văn B", "Ngô Thị C");
        model.addAttribute("users", users);

        return "index";
    }

    @GetMapping("/user")
    public String getUser(Model model) {
        return "user";
    }

    @GetMapping("/admin")
    public String getAdmin(Model model) {
        return "admin";
    }

    @GetMapping("/author")
    public String getAuthor(Model model) {
        return "author";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "login";
    }
}
