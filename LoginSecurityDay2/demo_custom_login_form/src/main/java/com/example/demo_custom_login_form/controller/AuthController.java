package com.example.demo_custom_login_form.controller;

import com.example.demo_custom_login_form.request.LoginRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/login-handle")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute("MY_SESSION", authentication.getName());

        return ResponseEntity.ok("Đăng nhập thành công");

    }
}
