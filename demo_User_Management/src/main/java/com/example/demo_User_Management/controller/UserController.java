package com.example.demo_User_Management.controller;

import com.example.demo_User_Management.dto.UserDTO;
import com.example.demo_User_Management.model.User;
import com.example.demo_User_Management.model.response.FileResponse;
import com.example.demo_User_Management.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public UserDTO updateUser(@PathVariable int id, @RequestBody User user){
        return iUserService.updateUser(id, user);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        iUserService.deleteUser(id);
    }
    @PutMapping("/{id}/update-avatar")
    public ResponseEntity<?> updateAvatar(@PathVariable int id, @ModelAttribute("file") MultipartFile file){
        FileResponse fileResponse = iUserService.updateUserAvatar(id,file);
        return ResponseEntity.ok(fileResponse);
    }
    @PostMapping("/{id}/forget-password")
    public ResponseEntity<?> forgetPassword(@PathVariable int id){
        String newPassword = iUserService.forgetPassword(id);
        return ResponseEntity.ok(newPassword);
    }
}
