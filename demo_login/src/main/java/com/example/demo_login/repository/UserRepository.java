package com.example.demo_login.repository;

import com.example.demo_login.db.UserDB;
import com.example.demo_login.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    public List<User> getAll(){
        return UserDB.list;
    }
}
