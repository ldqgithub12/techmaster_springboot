package com.example.thymeleafspringsecurity;

import com.example.thymeleafspringsecurity.repository.UserRepository;
import com.example.thymeleafspringsecurity.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class ThymeleafSpringsecurityApplicationTests {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Test
    void save_user() {
        User user = new User(null, "Hien", "hien@gmail.com", encoder.encode("111"), List.of("USER"));

        User admin = new User(null, "An", "an@gmail.com", encoder.encode("111"), List.of("USER", "ADMIN"));

        User author = new User(null, "Tuan", "tuan@gmail.com", encoder.encode("111"), List.of("AUTHOR"));

        userRepository.save(admin);
        userRepository.save(user);
        userRepository.save(author);
    }

}
