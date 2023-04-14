package com.example.thymeleafspringsecurity;

import com.example.thymeleafspringsecurity.entity.TokenConfirm;
import com.example.thymeleafspringsecurity.repository.RoleRepository;
import com.example.thymeleafspringsecurity.repository.TokenConfirmRepository;
import com.example.thymeleafspringsecurity.repository.UserRepository;
import com.example.thymeleafspringsecurity.entity.User;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class ThymeleafSpringsecurityApplicationTests {

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TokenConfirmRepository tokenConfirmRepository;

    @Test
    void save_user() {
        Faker faker = new Faker();
       for (int i = 0; i<10;i++){
           User user = User.builder()
                   .name(faker.leagueOfLegends().champion())
                   .email(faker.internet().emailAddress())
                   .password(encoder.encode("123"))
                   .enable(false)
                   .build();
           userRepository.save(user);
       }
    }

}
