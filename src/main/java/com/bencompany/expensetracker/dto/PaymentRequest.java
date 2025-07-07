package com.bencompany.expensetracker.dto;

import java.time.LocalDate;
import java.util.UUID;

public class PaymentRequest {
    private UUID userID;
    private int amount;
    private String category;
    private String notes;

    public UUID getUserID() { return userID; }
    public void setUserID(UUID userID) { this.userID = userID; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
