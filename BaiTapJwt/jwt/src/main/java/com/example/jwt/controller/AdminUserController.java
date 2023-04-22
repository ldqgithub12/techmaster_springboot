package com.example.jwt.controller;

import com.example.jwt.entity.Category;
import com.example.jwt.entity.User;
import com.example.jwt.request.UpsertCategoryRequest;
import com.example.jwt.request.UpsertUserRequest;
import com.example.jwt.services.IAdminServices;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminUserController {
    private final IAdminServices adminServices;

    public AdminUserController(IAdminServices adminServices) {
        this.adminServices = adminServices;
    }


    @GetMapping("/users")
    public Page<User> getAllUser(@RequestParam(required = false, defaultValue = "0") int page,
                                       @RequestParam(required = false, defaultValue = "5") int pageSize){
        return adminServices.getAllUser(page,pageSize);
    }
    @PostMapping("/users")
    public User addNewUser(@RequestBody UpsertUserRequest request){
        return adminServices.addNewUser(request);
    }
    @PutMapping("/users/{id}")
    public User updateBlog(@PathVariable int id, @RequestBody User blog){
        return adminServices.updateUser(id,blog);
    }
}
