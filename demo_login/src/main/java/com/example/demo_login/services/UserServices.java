package com.example.demo_login.services;

import com.example.demo_login.dto.UserDTO;
import com.example.demo_login.exceptions.NotFoundException;
import com.example.demo_login.models.User;
import com.example.demo_login.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServices {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServices(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserDTO> getAll() {
        List<User> list = userRepository.getAll();
        List<UserDTO> result = list.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return result;
    }

    public UserDTO login(String username, String password){
        List<User> list = userRepository.getAll();
        User user = list.stream().filter(user1 -> user1.getUsername().equalsIgnoreCase(username) && user1.getPassword().equals(password)).findFirst().orElse(null);
        if (user != null){
            UserDTO result = modelMapper.map(user, UserDTO.class);
            return result;
        }
        else {
            throw new NotFoundException("Username hoặc password chưa chính xác");
        }

    }
}
