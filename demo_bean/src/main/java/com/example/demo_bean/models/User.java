package com.example.demo_bean.models;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;

    @Autowired
    private Vehicle vehicle;
    public void sayHello(){
        System.out.println("Hello ");
    }
    public void getVehicle(){
        vehicle.run();
    }
}
