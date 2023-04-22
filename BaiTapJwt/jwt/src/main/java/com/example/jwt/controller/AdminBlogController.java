package com.example.jwt.controller;

import com.example.jwt.entity.Blog;
import com.example.jwt.request.UpsertBlogRequest;
import com.example.jwt.services.IAdminServices;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminBlogController {
    private final IAdminServices adminServices;

    public AdminBlogController(IAdminServices adminServices) {
        this.adminServices = adminServices;
    }

    @GetMapping("/blogs")
    public Page<Blog> getAllBlog(@RequestParam(required = false, defaultValue = "1") int page,
                                 @RequestParam(required = false, defaultValue = "5") int pageSize){
        return adminServices.getAllBlog(page,pageSize);
    }
    @GetMapping("/blogs/{id}")
    public Blog findById(@PathVariable int id){
        return adminServices.getBlogById(id);
    }
    @PostMapping("/blogs")
    public Blog addNewBlog(@RequestBody UpsertBlogRequest request){
        return adminServices.addNewBlog(request);
    }
    @PutMapping("/blogs/{id}")
    public Blog updateBlog(@PathVariable int id, @RequestBody Blog blog){
        return adminServices.updateBlog(id,blog);
    }

}
