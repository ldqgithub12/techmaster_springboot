package com.example.demo_Day_06.models;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class CreateTodoRequest {
    @NotEmpty(message = "title không được để trống")
    private String title;

//    @NotEmpty(message = "level không được để trống")
//    private String level;
}
