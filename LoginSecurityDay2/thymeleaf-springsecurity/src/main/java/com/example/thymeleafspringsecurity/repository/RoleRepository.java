package com.example.thymeleafspringsecurity.repository;

import com.example.thymeleafspringsecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}