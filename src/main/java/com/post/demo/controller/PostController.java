package com.post.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @GetMapping("/asdasd")
    public String getPost(){

        return "Post123";
    }
}
