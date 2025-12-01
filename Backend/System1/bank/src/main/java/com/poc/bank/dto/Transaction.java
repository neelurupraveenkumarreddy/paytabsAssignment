package com.poc.bank.dto;

import lombok.Data;

@Data
public class Transaction {
    private Long id;
    private String cardNumber;
    private String type;      // TOPUP / PURCHASE
    private Double amount;
    private String status;    // SUCCESS / FAILED
    private String timestamp; // or LocalDateTime if formatted properly
}
