package com.example.jwt.services;

import com.example.jwt.dto.CategoryDto;
import com.example.jwt.entity.Blog;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryService {
    Page<Blog> getAllBlog(int page, int pageSize);
    List<Blog> findBlog(String name);
    List<CategoryDto> getAllCategory();
    List<CategoryDto> categoryUsedMost(String top);
    List<Blog> getByCategory(String category);
    Blog getBlogByIdAndSlug(int id, String slug);
}
