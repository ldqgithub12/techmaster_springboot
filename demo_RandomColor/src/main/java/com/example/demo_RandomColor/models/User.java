package com.example.demo_RandomColor.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private Integer id;
    private String username;
    private String email;
    private String password ;
    private String avatar;

}
