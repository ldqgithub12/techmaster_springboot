package com.example.demo_User_Management.repository;

import com.example.demo_User_Management.db.UserDB;
import com.example.demo_User_Management.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    public List<User> getAll(){
        return UserDB.list;
    }
}
