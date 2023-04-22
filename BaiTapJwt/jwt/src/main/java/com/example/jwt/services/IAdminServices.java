package com.example.jwt.services;

import com.example.jwt.entity.Blog;
import com.example.jwt.entity.Category;
import com.example.jwt.entity.Comment;
import com.example.jwt.entity.User;
import com.example.jwt.request.UpsertBlogRequest;
import com.example.jwt.request.UpsertCategoryRequest;
import com.example.jwt.request.UpsertUserRequest;
import org.springframework.data.domain.Page;

public interface IAdminServices {
    Page<Blog> getAllBlog(int page, int pageSize);
    Blog getBlogById(int id);
    Blog addNewBlog(UpsertBlogRequest request);
    Blog updateBlog(int id, Blog blog);
    void deleteBlog(int id);
    Page<Blog> findBlogByKeyword(String keyword, int page, int pageSize);
    Page<Category> getAllCategory(int page, int pageSize);
    Category addNewCategory(UpsertCategoryRequest request);
    Category updateCategory(int id, Category category);
    void deleteCategory(int id);
    Page<User> getAllUser(int page, int pageSize);
    User addNewUser(UpsertUserRequest request);
    User updateUser(int id, User user);
    Page<Comment> getAllComment(int page, int pageSize);
    Comment updateComment(int id, Comment comment);
    void deleteComment(int id);
}
