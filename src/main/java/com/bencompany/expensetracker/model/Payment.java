package com.bencompany.expensetracker.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Payment {
    @Id
    @GeneratedValue(generator = "uuid2")
    @org.hibernate.annotations.GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @jakarta.persistence.Column(columnDefinition = "BINARY(16)")
    private UUID paymentID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false) // creates the FK column in Payment table
    private User user;

    private int amount;
    private String category;
    private LocalDateTime timeCreated;
    private String notes;

    public Payment(){

    }

    //getters
    public UUID getPaymentId(){return paymentID;}
    public User getUser(){return user;}
    public int getAmount(){return amount;}
    public String getCategory(){return category;}
    public LocalDateTime getTimeCreated(){return timeCreated;}
    public String getNotes(){return notes;}

    //setters
    public void setUser(User user) { this.user = user; }
    public void setAmount(int amount) { this.amount = amount; }
    public void setCategory(String category) { this.category = category; }
    public void setNotes(String notes) { this.notes = notes; }

    @PrePersist
    protected void onCreate(){
        this.timeCreated = LocalDateTime.now();
    }


}
