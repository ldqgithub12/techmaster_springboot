package com.example.thymeleafspringsecurity.repository;

import com.example.thymeleafspringsecurity.entity.TokenConfirm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenConfirmRepository extends JpaRepository<TokenConfirm, Integer> {
}