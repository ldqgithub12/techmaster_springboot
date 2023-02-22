package com.example.demo_Day_06.controller;

import com.example.demo_Day_06.models.PostRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WebController {
    @GetMapping("/home")
    public String getHomes(){
        return "Home";
    }
    @GetMapping("/home/{id}")
    public String getHomeId(@PathVariable String id){
        return "Home" + id;
    }

    @PostMapping("/post")
    public PostRequest postDemo(@RequestBody PostRequest postRequest){
        PostRequest postRequest1 = new PostRequest();
        postRequest1.setId(postRequest.getId());
        postRequest1.setTitle(postRequest.getTitle());
        postRequest1.setAuthor(postRequest.getAuthor());
        return  postRequest1;
    }
}
