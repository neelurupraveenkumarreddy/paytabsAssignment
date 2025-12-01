package com.poc.bank.dto;

import java.math.BigDecimal;

public class TransactionResponse {

    private String status; // "OK" or "FAIL"
    private String message;
    private BigDecimal balance;
    private Long transactionId;

    public TransactionResponse() {}

    public TransactionResponse(String status, String message, BigDecimal balance, Long transactionId) {
        this.status = status;
        this.message = message;
        this.balance = balance;
        this.transactionId = transactionId;
    }

    // Getters
    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    // Setters
    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
}
