package com.example.jwt.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertUserRequest {
    private String name;
    private String email;
    private String password;
    private List<Integer> roleIds; // Danh sách id của các role áp dụng
}
