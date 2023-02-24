package com.example.demo_User_Management.service;

import com.example.demo_User_Management.exception.NotFoundException;
import com.example.demo_User_Management.model.User;
import com.example.demo_User_Management.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.getAll();
    }

    @Override
    public User findById(int id) {
        List<User>list = userRepository.getAll();
        return list.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    @Override
    public User createNewUser(User user) {
        List<User> list = userRepository.getAll();
        list.add(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        List<User>list = userRepository.getAll();
        User userUpdate = list.stream().filter(user1 -> user1.getId() == user.getId()).findFirst().orElse(null);
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
        return userUpdate;
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
    public void forgetPassword(int id, String newPassword) {

    }
}
