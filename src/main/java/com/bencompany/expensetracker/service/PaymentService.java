package com.bencompany.expensetracker.service;

import com.bencompany.expensetracker.dto.PaymentDTO;
import com.bencompany.expensetracker.dto.PaymentRequest;
import com.bencompany.expensetracker.model.Payment;
import com.bencompany.expensetracker.model.User;
import com.bencompany.expensetracker.repository.PaymentRepository;
import com.bencompany.expensetracker.repository.UserRepository;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import jakarta.persistence.criteria.Predicate;

public class PaymentService{
    final private UserRepository userRepository;
    final private PaymentRepository paymentRepository;

    //Dependency Injection!
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


    public List<PaymentDTO> findPayments(UUID paymentId, UUID userId, String category, LocalDate startDate, LocalDate endDate) {
        Specification<Payment> spec = ((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>(); //hold the WHERE clauses (aka predicates)
            if(paymentId != null){
                predicates.add(cb.equal(root.get("paymentID"), paymentId));
            }
            if(userId != null){
                predicates.add(cb.equal(root.get("user").get("id"), userId));
            }
            if(category != null){
                predicates.add(cb.equal(cb.lower(root.get("category")), category.toLowerCase()));
            }
            if(startDate != null){
                predicates.add(cb.greaterThanOrEqualTo(root.get("timeCreated"), startDate));
            }
            if (endDate != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("timeCreated"), endDate));
            }
            return cb.and(predicates.toArray(new Predicate[0])); //creates Predicate[] and joins them with ANDs
        });

        List<Payment> payments = paymentRepository.findAll(spec);
        return payments.stream()
                .map(PaymentDTO::new)
                .collect(Collectors.toList());
    }
}
