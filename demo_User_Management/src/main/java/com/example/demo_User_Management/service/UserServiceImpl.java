package com.example.demo_User_Management.service;

import com.example.demo_User_Management.dto.UserDTO;
import com.example.demo_User_Management.exception.NotFoundException;
import com.example.demo_User_Management.model.User;
import com.example.demo_User_Management.model.response.FileResponse;
import com.example.demo_User_Management.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService{
    private final UserRepository userRepository;
    private final FileService fileService;
    private final MailServices mailServices;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, FileService fileService, MailServices mailServices, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.fileService = fileService;
        this.mailServices = mailServices;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDTO> getAllUser() {
       List<User> list = userRepository.getAll();
       List<UserDTO> userDTOList = list.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
       return userDTOList;
    }

    @Override
    public UserDTO findById(int id) {
        List<User>list = userRepository.getAll();
        User users =  list.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
        UserDTO userDTO = modelMapper.map(users, UserDTO.class);
        return userDTO;
    }

    @Override
    public UserDTO createNewUser(User user) {
        List<User> list = userRepository.getAll();
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        list.add(user);
        return userDTO;
    }

    @Override
    public UserDTO updateUser(int id, User user) {
        List<User>list = userRepository.getAll();
        User userUpdate = list.stream().filter(user1 -> user1.getId() == id).findFirst().orElse(null);
        if (userUpdate != null){
            userUpdate.setName(user.getName());
            userUpdate.setAddress(user.getAddress());
            userUpdate.setEmail(user.getEmail());
            userUpdate.setAvatar(user.getAvatar());
            userUpdate.setPassword(user.getPassword());
        }
        else {
            throw new NotFoundException("Không tồn tại User có id: "+user.getId());
        }
        UserDTO userDTO = modelMapper.map(user,UserDTO.class);
        return userDTO;
    }

    @Override
    public void deleteUser(int id) {
        List<User>list = userRepository.getAll();
        User user = list.stream().filter(user1 -> user1.getId() == id).findFirst().orElse(null);
        if (user != null){
            list.remove(user);
        }
        else {
            throw new NotFoundException("Không tồn tại user có id :"+id);
        }

    }

    @Override
    public String forgetPassword(int id) {
        List<User> list = userRepository.getAll();
        User user = list.stream().filter(user1 -> user1.getId() == id).findFirst().orElse(null);
        Random rd = new Random();
        if (user == null){
            throw new NotFoundException("User with id " +id +" doesn't exist");
        }
        String newPassword = String.valueOf(rd.nextInt(900)+100);
        user.setPassword(newPassword);
        mailServices.sendMail(user.getEmail(), "Quên mật khẩu","Mật khẩu mới là: "+newPassword);
        return newPassword;
    }
    public FileResponse updateUserAvatar(int id,MultipartFile file){
        List<User> list = userRepository.getAll();
        User user = list.stream().filter(user1 -> user1.getId() == id).findFirst().orElse(null);
        if (user == null){
            throw new NotFoundException("User with id "+ id +" doesn't exist");
        }
        FileResponse fileResponse = fileService.uploadFile(file);
        user.setAvatar(fileResponse.getUrl());
        return fileResponse;
    }
}
