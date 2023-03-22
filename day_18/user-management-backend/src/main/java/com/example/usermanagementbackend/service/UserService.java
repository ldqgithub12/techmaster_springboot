package com.example.usermanagementbackend.service;

import com.example.usermanagementbackend.exception.BadRequestException;
import com.example.usermanagementbackend.exception.NotFoundException;
import com.example.usermanagementbackend.model.User;
import com.example.usermanagementbackend.model.dto.UserDto;
import com.example.usermanagementbackend.model.mapper.UserMapper;
import com.example.usermanagementbackend.model.request.CreateUserRequest;
import com.example.usermanagementbackend.model.request.UpdatePasswordRequest;
import com.example.usermanagementbackend.model.request.UpdateUserRequest;
import com.example.usermanagementbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//    private final FileService fileService;
//    private final MailService mailService;
//
//    // Lấy danh sách user ở dạng DTO
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }
//
//    // Tìm kiếm user theo tên
    public List<UserDto> searchUser(String name) {
        return userRepository.findByName(name)
                .stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }
//
//    // Lấy thông tin của user theo id
    public UserDto getUserById(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found user with id = " + id);
        });
        UserDto userDto = UserMapper.toUserDto(user);
        return userDto;
    }
//
//        return UserMapper.toUserDto(user);
//    }
//
//    // Xóa user
    public void deleteUser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found user with id = " + id);
        });
        userRepository.deleteById(user.getId());
    }
//
//    // Tạo user mới
    public UserDto createUser(CreateUserRequest request) {
        if (userRepository.findByEmail(request.getEmail()) == null) {
            throw new BadRequestException("Email = " + request.getEmail() + " is existed");
        }

        Random rd = new Random();
        User user = new User();
        user.setId(rd.nextInt(100));
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setPassword(request.getPassword());

        userRepository.save(user);

        return UserMapper.toUserDto(user);
    }
//
    // Cập nhật thông tin của user
    public UserDto updateUser(int id, UpdateUserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found user with id = " + id);
        });

        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());

        return UserMapper.toUserDto(user);
    }
//
//    // Cập nhật password mới
    public void updatePassword(int id, UpdatePasswordRequest request) {
        // Kiểm tra có tồn tại hay không
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found user with id = " + id);
        });

        // Kiểm tra oldPassword có đúng không
        if (!user.getPassword().equals(request.getOldPassword())) {
            throw new BadRequestException("old password is incorrect!");
        }

        // Kiểm tra oldPassword có = newPassword không
        if (request.getNewPassword().equals(request.getOldPassword())) {
            throw new BadRequestException("old password and new password cannot be the same!");
        }

        // Cập nhật newPassword cho user tương ứng
        user.setPassword(request.getNewPassword());
        userRepository.save(user);
    }
//
//    // Quên mật khẩu
//    public String forgotPassword(int id) {
//        // Kiểm tra user có tồn tại hay không
//        User user = userRepository.findById(id).orElseThrow(() -> {
//            throw new NotFoundException("Not found user with id = " + id);
//        });
//        // Random chuỗi password mới cho user (100 -> 999)
//        Random rd = new Random();
//        String newPassword = String.valueOf(rd.nextInt(900) + 100);
//
//        // Lấy thông tin của user và đặt lại password mới cho user
//        user.setPassword(newPassword);
//
//        // Gửi mail
//        mailService.sendMail(
//                user.getEmail(),
//                "Quên mật khẩu",
//                "Mật khẩu mới : " + newPassword
//        );
//
//        // Trả về thông tin password mới
//        return newPassword;
//    }
//
//    public FileResponse updateAvatar(Integer id, MultipartFile file) {
//        // Tìm kiếm xem có tồn tại user không
//        // Nếu k thì báo lỗi
//        User user = userRepository.findById(id).orElseThrow(() -> {
//            throw new NotFoundException("Not found user with id = " + id);
//        });
//
//        // Upload file
//        FileResponse fileResponse = fileService.uploadFile(file);
//
//        // Set lại avatar của user
//        user.setAvatar(fileResponse.getUrl());
//
//       return fileResponse;
//    }
}
