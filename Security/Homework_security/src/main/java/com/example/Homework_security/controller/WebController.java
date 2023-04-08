package com.example.Homework_security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class WebController {
    @GetMapping("/")
    public ResponseEntity<?> ViewDashBoard(){
        return ResponseEntity.ok("Dashboard");
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/manage-user")
    public ResponseEntity<?> ManageUser(){
        return ResponseEntity.ok("Manage user");
    }
    @Secured({"ROLE_ADMIN","ROLE_SALE"})
    @GetMapping("/manage-category")
    public ResponseEntity<?> ManageCategory(){
        return ResponseEntity.ok("Manage category");
    }
    @Secured({"ROLE_ADMIN","ROLE_SALE"})
    @GetMapping("/manage-product")
    public ResponseEntity<?> ManageProduct(){
        return ResponseEntity.ok("Manage Product");
    }
    @Secured({"ROLE_ADMIN","ROLE_SALE"})
    @GetMapping("/manage-brand")
    public ResponseEntity<?> ManageBrand(){
        return ResponseEntity.ok("Manage brand");
    }
    @Secured({"ROLE_ADMIN","ROLE_SALE"})
    @GetMapping("/manage-order")
    public ResponseEntity<?> ManageOrder(){
        return ResponseEntity.ok("Manage order");
    }
    @Secured({"ROLE_AMIN","ROLE_SALE","ROLE_AUTHOR"})
    @GetMapping("/manage-articles")
    public ResponseEntity<?> ManageArticles(){
        return ResponseEntity.ok("Manage article");
    }
    @Secured("ROLE_USER")
    @GetMapping("/user-info")
    public ResponseEntity<?> ViewUserInfo(Principal principal){
        return ResponseEntity.ok(principal);
    }
}
