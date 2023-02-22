package com.example.demo_RandomColor.models;

import lombok.*;
import org.springframework.web.bind.annotation.Mapping;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BmiRequest {
    private Double weight;
    private Double height;
}
