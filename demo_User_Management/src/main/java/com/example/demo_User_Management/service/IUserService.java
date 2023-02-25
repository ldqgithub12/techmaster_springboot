package com.example.demo_User_Management.service;

import com.example.demo_User_Management.dto.UserDTO;
import com.example.demo_User_Management.model.User;
import org.apache.catalina.LifecycleState;



import java.util.List;

public interface IUserService {
    List<UserDTO> getAllUser();
    UserDTO findById(int id);
    UserDTO createNewUser(User user);
    UserDTO updateUser(User user);
    void deleteUser(int id);
    void forgetPassword(int id, String newPassword);
}
