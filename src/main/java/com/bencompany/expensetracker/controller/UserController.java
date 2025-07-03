package com.bencompany.expensetracker.controller;

import com.bencompany.expensetracker.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){


    }
}
