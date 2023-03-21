package com.example.demojpa;

import com.example.demojpa.dto.UserDto;
import com.example.demojpa.entity.User;
import com.example.demojpa.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

//@SpringBootTest
// Chỉ làm việc với database in-memory : h2 database
// Tạo các bean cần thiết, không chạy cả ứng dụng như @SpringBootTest
// Muốn sử dụng Mysql cần overide cấu hình @DataJpaTest
// @DataJpaTest tự động rollback lại dữ liệu
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DemoJpaApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback(value = false)
    void save_user() {
        Faker faker = new Faker();
        for (int i = 0; i < 30; i++) {
            User user = User.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .password("111")
                    .build();

            userRepository.save(user);
        }
    }

    @Test
    void get_all_user() {
        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);
    }

    @Test
    void get_by_id() {
        Optional<User> userOptional = userRepository.findById(31);
        if(userOptional.isPresent()) {
            System.out.println(userOptional.get());
        } else {
            throw new RuntimeException("Not found user");
        }
    }

    @Test
    @Rollback(value = false)
    void update_user() {
        Optional<User> userOptional = userRepository.findById(31);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName("Bùi Hiên");
            user.setEmail("hien@gmail.com");

            userRepository.save(user);
        } else {
            throw new RuntimeException("Not found user");
        }
    }

    @Test
    @Rollback(value = false)
    void delete_user() {
        // Theo id
        userRepository.deleteById(31);

        // Theo object
        Optional<User> userOptional = userRepository.findById(32);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.delete(user);
        } else {
            throw new RuntimeException("Not found user");
        }
    }

    @Test
    void findUserDtoBy() {
        List<UserDto> userDtos = userRepository.findUserDtoByName("Alden Oberbrunner");
        userDtos.forEach(System.out::println);
    }

    @Test
    void getUserDtoUsingNativeQuery() {
        List<UserDto> userDtos = userRepository.getUserDtoUsingNativeQuery();
        userDtos.forEach(System.out::println);
    }
}
