package com.example.pdfgenerator.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequest {
    
    @NotBlank(message = "Seller name is required")
    private String seller;
    
    @NotBlank(message = "Seller GSTIN is required")
    private String sellerGstin;
    
    @NotBlank(message = "Seller address is required")
    private String sellerAddress;
    
    @NotBlank(message = "Buyer name is required")
    private String buyer;
    
    @NotBlank(message = "Buyer GSTIN is required")
    private String buyerGstin;
    
    @NotBlank(message = "Buyer address is required")
    private String buyerAddress;
    
    @NotEmpty(message = "At least one item is required")
    private List<Item> items;

    // Simple method to calculate total amount
    public double calculateTotal() {
        double total = 0.0;
        for (Item item : items) {
            total += item.getAmount();
        }
        return total; // Ensure the return statement is correctly placed
    }
}
