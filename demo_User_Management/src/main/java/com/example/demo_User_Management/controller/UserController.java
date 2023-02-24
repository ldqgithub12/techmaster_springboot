package com.example.demo_User_Management.controller;

import com.example.demo_User_Management.model.User;
import com.example.demo_User_Management.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/users")
public class UserController {
    private final IUserService iUserService;

    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @GetMapping("/")
    public List<User> getAllUser(){
        return iUserService.getAllUser();
    }
    @GetMapping("/{id}")
    public User findById(@PathVariable int id){
        return iUserService.findById(id);
    }
    @PostMapping("/")
    public User addNewUser(@RequestBody User user){
        return iUserService.createNewUser(user);
    }
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user){
        return iUserService.updateUser(user);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        iUserService.deleteUser(id);
    }
}
