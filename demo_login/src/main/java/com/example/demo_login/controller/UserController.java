package com.example.demo_login.controller;

import com.example.demo_login.dto.UserDTO;
import com.example.demo_login.services.UserServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }
    @GetMapping("/")
    public List<UserDTO> getAll(){
        return userServices.getAll();
    }
    @PostMapping("/login")
    public UserDTO login(@RequestParam String username, @RequestParam String password){
        return userServices.login(username,password);
    }
}
