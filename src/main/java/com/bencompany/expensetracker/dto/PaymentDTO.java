package com.bencompany.expensetracker.dto;

import com.bencompany.expensetracker.model.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentDTO {
    private UUID userID;
    private UUID paymentID;
    private double amount;
    private String category;
    private String notes;
    private LocalDateTime date;

    public PaymentDTO(Payment payment){
        this.userID = payment.getUser().getId();
        this.paymentID = payment.getPaymentId();
        this.amount = payment.getAmount();
        this.category = payment.getCategory();
        this.notes = payment.getNotes();
        this.date = payment.getTimeCreated();
    }

}
