package com.bencompany.expensetracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Payment {
    @Id
    @GeneratedValue(generator = "uuid2")
    @org.hibernate.annotations.GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @jakarta.persistence.Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private int amount;
    private String category;
    private LocalDateTime timeCreated;
    private String notes;

    public Payment(){

    }
    public UUID getId(){return id;}
    public int getAmount(){return amount;}
    public String getCategory(){return category;}
    public LocalDateTime getTimeCreated(){return timeCreated;}
    public String getNotes(){return notes;}

    @PrePersist
    protected void onCreate(){
        this.timeCreated = LocalDateTime.now();
    }


}
