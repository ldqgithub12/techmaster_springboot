package com.example.demo_bean.models;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class bike implements Vehicle{
    @Override
    public void run() {
        System.out.println("Di chuyen bang xe dap");
    }
}
