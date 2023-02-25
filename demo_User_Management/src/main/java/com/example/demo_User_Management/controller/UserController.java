package com.example.demo_User_Management.controller;

import com.example.demo_User_Management.dto.UserDTO;
import com.example.demo_User_Management.model.User;
import com.example.demo_User_Management.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/api/users")
public class UserController {
    private final IUserService iUserService;

    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @GetMapping("/")
    public List<UserDTO> getAllUser(){
        return iUserService.getAllUser();
    }
    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable int id){
        return iUserService.findById(id);
    }
    @PostMapping("/")
    public UserDTO addNewUser(@RequestBody @Valid User user){
        return iUserService.createNewUser(user);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@RequestBody User user){
        return iUserService.updateUser(user);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        iUserService.deleteUser(id);
    }
}
