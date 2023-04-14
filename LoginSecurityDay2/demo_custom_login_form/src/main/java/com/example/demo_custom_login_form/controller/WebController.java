package com.example.demo_custom_login_form.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WebController {
    @GetMapping("/")
    public String getHome(Model model){
        model.addAttribute("name","Quang Le Dang");
        List<String> list = List.of("Nguyen Van A","Tran Van B");
        model.addAttribute("users",list);
        return "index";
    }
    @GetMapping("/author")
    public String getAuthor(){
        return "author";
    }
    @GetMapping("/user")
    public String getUser(){
        return "user";
    }
    @GetMapping("/admin")
    public String getAdmin(){
        return "admin";
    }
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
}
