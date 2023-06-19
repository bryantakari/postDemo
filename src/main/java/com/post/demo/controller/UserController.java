package com.post.demo.controller;

import com.post.demo.model.User;
import com.post.demo.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController{
    @Autowired
    UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/user/create")
    public ResponseEntity<User> createUser(@RequestBody User newUser){
        try{
            User temp = new User();
            temp.setEmail(newUser.getEmail());
            temp.setPassword(newUser.getPassword());
            temp.setUsername(newUser.getUsername());
            User _user = userRepository.save(temp);
            log.info("user created Success!");
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUser(){
        try{
            List<User> _user = userRepository.findAll();
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
