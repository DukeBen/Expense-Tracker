package com.bencompany.expensetracker.controller;

import com.bencompany.expensetracker.dto.PaymentDTO;
import com.bencompany.expensetracker.dto.PaymentRequest;
import com.bencompany.expensetracker.model.Payment;
import com.bencompany.expensetracker.repository.PaymentRepository;
import com.bencompany.expensetracker.repository.UserRepository;
import com.bencompany.expensetracker.service.PaymentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getPayments(
            @RequestParam(required = false) UUID paymentId,
            @RequestParam(required = false) UUID userId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        List<PaymentDTO> payments = paymentService.findPayments(paymentId, userId, category, startDate, endDate);

        if (payments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(payments);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable UUID paymentId){
        paymentRepository.deleteById(paymentId);
    }
}
