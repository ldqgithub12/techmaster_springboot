package com.example.demo_RandomColor.controller;

import com.example.demo_RandomColor.models.BmiRequest;
import com.example.demo_RandomColor.models.PostRequestUser;
import com.example.demo_RandomColor.models.User;
import com.example.demo_RandomColor.services.RdColorService;
import org.springframework.web.bind.annotation.*;

@RestController
public class RdColorController {
    private final RdColorService colorService;

    public RdColorController(RdColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping("/random-color")
    public String rdColor(@RequestParam int type){
        return colorService.randomService(type);
    }

    @GetMapping("/bmi")
    public Double bmiGet(@RequestParam double weight, @RequestParam double height){
        return colorService.calculateBMI(weight,height);
    }
    @PostMapping("/bmi")
    public Double bmiPost(@RequestBody BmiRequest bmiRequest) {
        return colorService.calculateBMI(bmiRequest.getWeight(), bmiRequest.getHeight());
    }
    @PostMapping("/login")
    public User login(@RequestBody PostRequestUser postRequestUser){
        return colorService.login(postRequestUser);
    }
}
