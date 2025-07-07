package com.bencompany.expensetracker.service;

import com.bencompany.expensetracker.dto.PaymentRequest;
import com.bencompany.expensetracker.model.Payment;
import com.bencompany.expensetracker.model.User;
import com.bencompany.expensetracker.repository.PaymentRepository;
import com.bencompany.expensetracker.repository.UserRepository;
import org.springframework.http.ResponseEntity;

public class PaymentService{
    final private UserRepository userRepository;
    final private PaymentRepository paymentRepository;

    public PaymentService(UserRepository userRepository, PaymentRepository paymentRepository){
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
    }


    public Payment createPayment(PaymentRequest paymentRequest){
        Payment newPayment = new Payment();
        User user = userRepository.findById(paymentRequest.getUserID())
                .orElseThrow(() -> new RuntimeException("User not found"));

        newPayment.setUser(user);
        newPayment.setAmount(paymentRequest.getAmount());
        newPayment.setCategory(paymentRequest.getCategory());
        newPayment.setNotes(paymentRequest.getNotes());
        return paymentRepository.save(newPayment);
    }


}
