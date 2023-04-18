package com.example.jwt.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private String comment;
    private String createdAt;
    private String updateAt;
    private String username;
}
