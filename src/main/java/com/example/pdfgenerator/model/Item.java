package com.example.pdfgenerator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    
    @NotBlank(message = "Item name is required")
    private String name;
    
    @NotBlank(message = "Quantity is required")
    private String quantity;
    
    @NotNull(message = "Rate is required")
    @Positive(message = "Rate must be positive")
    private Double rate;
    
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;
    
    // Explicit getter for amount
    public Double getAmount() {
        return this.amount;
    }
    
    // Explicit setter for amount
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
