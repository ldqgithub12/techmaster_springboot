package com.example.demo_bean.models;

import org.springframework.stereotype.Component;

@Component
public class bus implements Vehicle{
    @Override
    public void run() {
        System.out.println("Di chuyen bang xe bus");
    }
}
