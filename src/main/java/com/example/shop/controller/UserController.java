package com.example.shop.controller;

import com.example.shop.entity.UserEntity;
import com.example.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getAllUser() {
        List<UserEntity> list = userService.getAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<List<UserEntity>> createUser(@RequestBody UserEntity user) {
        userService
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
