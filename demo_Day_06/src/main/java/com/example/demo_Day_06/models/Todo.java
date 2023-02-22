package com.example.demo_Day_06.models;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Todo {
    private int id;
    private String title;
    private Boolean status;
//    private LocalDateTime createAt;
//    private String level;
}
