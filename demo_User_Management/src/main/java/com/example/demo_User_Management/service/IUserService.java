package com.example.demo_User_Management.service;

import com.example.demo_User_Management.model.User;
import org.apache.catalina.LifecycleState;



import java.util.List;

public interface IUserService {
    List<User> getAllUser();
    User findById(int id);
    User createNewUser(User user);
    User updateUser(User user);
    void deleteUser(int id);
    void forgetPassword(int id, String newPassword);
}
