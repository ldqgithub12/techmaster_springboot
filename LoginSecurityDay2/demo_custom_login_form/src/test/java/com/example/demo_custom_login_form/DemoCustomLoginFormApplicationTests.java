package com.example.demo_custom_login_form;

import com.example.demo_custom_login_form.repository.UserRepository;
import com.example.demo_custom_login_form.entity.User;
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
class DemoCustomLoginFormApplicationTests {
	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}
	@Test
	void add_user(){
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			User admin=new User(1,"Quang","quang@gmail.com",encoder.encode("123"), List.of("USER"));
			User user = new User(2,"Quang1","quang1@gmail.com",encoder.encode("123"),List.of("USER","ADMIN"));
			User author = new User(3,"Quang2","quang2@gmail.com",encoder.encode("123"),List.of("USER"));
			userRepository.save(admin);
			userRepository.save(user);
			userRepository.save(author);
	}

}
