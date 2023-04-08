package com.example.demo_security.controller;

import com.example.demo_security.interfaces.IAuthenticationCustom;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class WebController {
    private final IAuthenticationCustom authenticationCustom;

    public WebController(IAuthenticationCustom authenticationCustom) {
        this.authenticationCustom = authenticationCustom;
    }

    @GetMapping("/")
    public ResponseEntity<?> getHome(){
        return ResponseEntity.ok("Home");
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("user")
    public ResponseEntity<?> getUser(){
        return ResponseEntity.ok("User");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("admin")
    public ResponseEntity<?> getAdmin(){
        return ResponseEntity.ok("Admin");
    }
    @Secured("ROLE_AUTHOR")
    @GetMapping("author")
    public ResponseEntity<?> getAuthor(){
        return ResponseEntity.ok("Author");
    }
    @GetMapping("user-infor")
    public ResponseEntity<?> getUserInfor(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(authentication);
    }
    @GetMapping("user-info-1")
    public ResponseEntity<?> getUserInfo1(Principal principal){
        return ResponseEntity.ok(principal);
    }
    @GetMapping("user-info-2")
    public ResponseEntity<?> getUserInfo2(Authentication authentication){
        return ResponseEntity.ok(authentication);
    }
    @GetMapping("user-info-custom")
    public ResponseEntity<?> getUserCustom(){
        return ResponseEntity.ok(authenticationCustom.getAuthentication());
    }
}
