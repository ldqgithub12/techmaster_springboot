package com.example.thymeleafspringsecurity.controller;

import com.example.thymeleafspringsecurity.request.LoginRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("login-handle")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session) {
        // Tạo đối tượng xác thực
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );

        // Tiến hành xác thực
        Authentication authentication = authenticationManager.authenticate(token);

        // Lưu dữ liệu vào context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Tạo ra session
        session.setAttribute("MY_SESSION", authentication.getName());

        return ResponseEntity.ok("Login thành công");
    }
}
