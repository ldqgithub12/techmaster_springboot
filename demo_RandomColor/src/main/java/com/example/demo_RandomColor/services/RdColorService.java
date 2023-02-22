package com.example.demo_RandomColor.services;

import com.example.demo_RandomColor.models.PostRequestUser;
import com.example.demo_RandomColor.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RdColorService {
    private List<User> listUser;

    public RdColorService() {
        listUser = new ArrayList<>();
        listUser.add(new User(1,"user1","user1@gmail.com","123456","avatarne"));
        listUser.add(new User(2,"user2","user2@gmail.com","123456","avatarne"));
        listUser.add(new User(3,"user3","user3@gmail.com","123456","avatarne"));
        listUser.add(new User(4,"user4","user4@gmail.com","123456","avatarne"));

    }

    public String randomService(int type){
        return switch(type){
            case 1-> randomColorName();
            case 2-> randomHexColor();
            case 3-> randomRgbColor();
            default->throw new RuntimeException("type ko hợp lệ");
        };
    }

    private String randomRgbColor() {
        Random rd = new Random();
        int r = rd.nextInt(255);
        int g = rd.nextInt(255);
        int b = rd.nextInt(255);
        return String.format(("rgb(%d,%d%d)"),r,g,b);
    }

    private String randomHexColor() {
        return null;
    }

    private String randomColorName() {
        List<String> colors = new ArrayList<>(List.of("black","blue","green","red"));
        Random rd = new Random();
        return colors.get(rd.nextInt(colors.size()));
    }
    public double calculateBMI(double weight, double height){
        return weight/(height*height);
    }
    public User login(@RequestBody PostRequestUser postRequestUser){
        return listUser.stream().filter(user -> user.getUsername().equals(postRequestUser.getUsername())&&user.getPassword().equals(postRequestUser.getPassword()))
                .findAny()
                .orElseThrow(()->{
                    throw new RuntimeException("username or password is not correct");
                });
    }
}
