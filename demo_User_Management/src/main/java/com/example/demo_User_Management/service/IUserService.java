package com.example.demo_User_Management.service;

import com.example.demo_User_Management.dto.UserDTO;
import com.example.demo_User_Management.model.User;
import com.example.demo_User_Management.model.response.FileResponse;
import org.apache.catalina.LifecycleState;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public interface IUserService {
    List<UserDTO> getAllUser();
    UserDTO findById(int id);
    UserDTO createNewUser(User user);
    UserDTO updateUser(int id, User user);
    void deleteUser(int id);
    String forgetPassword(int id);
    public FileResponse updateUserAvatar(int id, MultipartFile file);
}
