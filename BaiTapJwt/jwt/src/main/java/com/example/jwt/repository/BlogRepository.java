package com.example.jwt.repository;

import com.example.jwt.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findByTitleContains(String name);
    @Query("SELECT b FROM Blog b WHERE b.status = true")
    Page<Blog> findAllByStatusTrue(Pageable pageable);


    @Query("SELECT COUNT(b) FROM Blog b JOIN b.categories c WHERE c.id = :categoryId AND b.status = true")
    Long countByCategoriesId(@Param("categoryId") int id);
    List<Blog> findByCategoriesName(String name);
    Blog findByIdAndSlug(int id, String slug);
}