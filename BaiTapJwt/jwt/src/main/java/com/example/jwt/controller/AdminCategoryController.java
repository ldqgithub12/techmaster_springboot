package com.example.jwt.controller;

import com.example.jwt.entity.Blog;
import com.example.jwt.entity.Category;
import com.example.jwt.request.UpsertBlogRequest;
import com.example.jwt.request.UpsertCategoryRequest;
import com.example.jwt.services.IAdminServices;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminCategoryController {
    private final IAdminServices adminServices;

    public AdminCategoryController(IAdminServices adminServices) {
        this.adminServices = adminServices;
    }


    @GetMapping("/categories")
    public Page<Category> getAllCategories(@RequestParam(required = false, defaultValue = "1") int page,
                                           @RequestParam(required = false, defaultValue = "5") int pageSize){
        return adminServices.getAllCategory(page,pageSize);
    }
    @PostMapping("/categories")
    public Category addNewCategory(@RequestBody UpsertCategoryRequest request){
        return adminServices.addNewCategory(request);
    }
    @PutMapping("/categories/{id}")
    public Category updateBlog(@PathVariable int id, @RequestBody Category blog){
        return adminServices.updateCategory(id,blog);
    }
}
