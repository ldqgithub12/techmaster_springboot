package com.example.jwt.controller;

import com.example.jwt.dto.CategoryDto;
import com.example.jwt.entity.Blog;
import com.example.jwt.entity.User;
import com.example.jwt.exceptions.CustomException;
import com.example.jwt.services.ICategoryService;
import lombok.Getter;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/public")
public class CategoryController {
    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/blogs")
    public Page<Blog> getAllBlog(@RequestParam(required = false, defaultValue = "0") int page,
                                 @RequestParam(required = false, defaultValue = "5") int pageSize)
    {
        Page<Blog> result = categoryService.getAllBlog(page,pageSize);
        Hibernate.initialize(result);
        return result;
    }

    @GetMapping("/search")
    public List<Blog> searchBlog(@RequestParam(required = false) String name){
        return categoryService.findBlog(name);
    }
    @GetMapping("/categories")
    public List<CategoryDto> getAllCategory(){
        return categoryService.getAllCategory();
    }
    @GetMapping("/categories/{categoryName}")
    public List<Blog> findByCategoryName(@PathVariable String categoryName){
        return categoryService.getByCategory(categoryName);
    }
    @GetMapping("/blogs/{id}/{slug}")
    public Blog findByIdAndSlug(@PathVariable int id, @PathVariable String slug){
        return categoryService.getBlogByIdAndSlug(id,slug);
    }
    @GetMapping("/category-top/{top}")
    public List<CategoryDto> categoriesUsedMost(@PathVariable String top){
        return categoryService.categoryUsedMost(top);
    }
}
