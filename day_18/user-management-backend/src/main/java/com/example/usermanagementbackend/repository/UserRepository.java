package com.example.usermanagementbackend.repository;

import com.example.usermanagementbackend.database.UserDatabase;
import com.example.usermanagementbackend.model.User;
import com.example.usermanagementbackend.model.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByName(String name);
    String findByEmail(String email);
}
