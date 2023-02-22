package com.example.demo_Day_06.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateTodoRequest {
    private String title;
    private Boolean status;
//    private String level;
}
