package com.example.jwt.controller;

import com.example.jwt.entity.Comment;
import com.example.jwt.entity.User;
import com.example.jwt.request.UpsertUserRequest;
import com.example.jwt.services.IAdminServices;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminCommentController {
    private final IAdminServices adminServices;

    public AdminCommentController(IAdminServices adminServices) {
        this.adminServices = adminServices;
    }
    @GetMapping("/comments")
    public Page<Comment> getAllUser(@RequestParam(required = false, defaultValue = "0") int page,
                                    @RequestParam(required = false, defaultValue = "5") int pageSize){
        return adminServices.getAllComment(page,pageSize);
    }
    @PutMapping("/comments/{id}")
    public Comment updateBlog(@PathVariable int id, @RequestBody Comment blog){
        return adminServices.updateComment(id,blog);
    }
    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable int id){
        adminServices.deleteComment(id);
    }
}
