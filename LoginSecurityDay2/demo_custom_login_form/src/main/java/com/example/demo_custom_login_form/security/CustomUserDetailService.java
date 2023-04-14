package com.example.demo_custom_login_form.security;

import com.example.demo_custom_login_form.entity.User;
import com.example.demo_custom_login_form.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("not found user");
                });
    }
}
