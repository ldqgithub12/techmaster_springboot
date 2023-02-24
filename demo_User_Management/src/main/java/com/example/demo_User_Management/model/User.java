package com.example.demo_User_Management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    @NotEmpty(message = "Tên không được để trống")
    private String name;
    private String email;
    private String phone;
    private String address;
    private String avatar;
    private String password;
}
