package com.example.jwt.repository;

import com.example.jwt.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findByTitleContains(String name);
    Long countByCategoriesId(int id);
    List<Blog> findByCategoriesName(String name);
    Blog findByIdAndSlug(int id, String slug);
}