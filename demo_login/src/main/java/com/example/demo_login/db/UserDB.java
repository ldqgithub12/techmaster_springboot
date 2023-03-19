package com.example.demo_login.db;

import com.example.demo_login.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
    public static List<User> list = new ArrayList<>(List.of(
            new User(1,"nguyenvana","a@gmail.com","abc@123",""),
            new User(2,"nguyenvanb","b@gmail.com","abc@123",""),
            new User(3,"nguyenvanc","c@gmail.com","abc@123","")
    ));
}
