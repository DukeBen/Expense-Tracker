package com.bencompany.expensetracker.controller;

import com.bencompany.expensetracker.model.Payment;
import com.bencompany.expensetracker.model.User;
import com.bencompany.expensetracker.repository.PaymentRepository;
import com.bencompany.expensetracker.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    public UserController(UserRepository userRepository, PaymentRepository paymentRepository){
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userRepository.save(user);

        URI location = URI.create("/users/" + savedUser.getId());
        return ResponseEntity.created(location).body(savedUser);
    }

    @GetMapping
    public ResponseEntity<User> getUser(@PathVariable UUID id){
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment){

        Payment savedPayment = paymentRepository.save(payment);

        URI location = URI.create("payments/" + savedPayment.getId());
        return ResponseEntity.created(location).body(savedPayment);
    }
}
