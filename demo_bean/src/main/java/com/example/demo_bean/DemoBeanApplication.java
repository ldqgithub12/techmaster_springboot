package com.example.demo_bean;

import com.example.demo_bean.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class DemoBeanApplication {
//	@Bean("user1")
//	public User user(){
//		return new User("Quang");
//	}
	@Bean
	int random(){
		return new Random().nextInt(100);
	}
	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(DemoBeanApplication.class, args);
		User user = context.getBean(User.class);
//		User user1 = context.getBean("user1",User.class);
		user.sayHello();
		user.getVehicle();
//		System.out.println(user1.getName());
	}

}
