package com.example.jwt.repository;

import com.example.jwt.entity.Comment;
import com.example.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByBlogId(int id);
    @Query("SELECT b.name FROM Comment a JOIN a.user b WHERE a.id = :commentId")
    String findUserByCommentId(@Param("commentId") int id);
}