package com.example.demo_custom_login_form.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();
    //Danh sach user
    List<User> list = new ArrayList<>(List.of(
            new User("1","Quang","quang@gmail.com",encoder.encode("123"),List.of("USER")),
            new User("2","Quang1","quang1@gmail.com",encoder.encode("123"),List.of("USER","ADMIN")),
            new User("3","Quang2","quang2@gmail.com",encoder.encode("123"),List.of("USER")),
            new User("4","Quang3","quang3@gmail.com",encoder.encode("123"),List.of("USER"))
    ));
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return
                list.stream().filter(p->p.getEmail().equals(email)).findFirst().orElseThrow(()->{
                    throw new UsernameNotFoundException("Not Found");
                });
    }
}
