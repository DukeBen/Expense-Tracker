package com.bencompany.expensetracker.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
//@Entity means this Class is a table in the DB
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @org.hibernate.annotations.GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @jakarta.persistence.Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String name;
    private String email;
    private LocalDateTime timeCreated;

    public User(){
        //constructor for JPA
    }

    public UUID getId() {
        return id;
    }
    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    @PrePersist
    protected void onCreate(){
        this.timeCreated = LocalDateTime.now();
    }
}
