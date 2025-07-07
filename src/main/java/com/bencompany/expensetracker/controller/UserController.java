package com.bencompany.expensetracker.controller;

import com.bencompany.expensetracker.model.Payment;
import com.bencompany.expensetracker.model.User;
import com.bencompany.expensetracker.repository.PaymentRepository;
import com.bencompany.expensetracker.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userRepository.save(user);

        URI location = URI.create("/users/" + savedUser.getId());
        return ResponseEntity.created(location).body(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id){
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
