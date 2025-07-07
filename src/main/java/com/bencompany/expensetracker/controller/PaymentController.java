package com.bencompany.expensetracker.controller;

import com.bencompany.expensetracker.dto.PaymentRequest;
import com.bencompany.expensetracker.model.Payment;
import com.bencompany.expensetracker.repository.PaymentRepository;
import com.bencompany.expensetracker.repository.UserRepository;
import com.bencompany.expensetracker.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentService paymentService;
    public PaymentController(UserRepository userRepository, PaymentRepository paymentRepository, PaymentService paymentService){
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentRequest paymentRequest){
        Payment savedPayment = paymentService.createPayment(paymentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);
    }
}
