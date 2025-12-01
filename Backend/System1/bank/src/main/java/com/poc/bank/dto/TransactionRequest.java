package com.poc.bank.dto;

import java.math.BigDecimal;

public class TransactionRequest {

    private String cardNumber;
    private String pin;
    private BigDecimal amount;
    private String type; // "TOPUP" or "PURCHASE"

    public TransactionRequest() {}

    public TransactionRequest(String cardNumber, String pin, BigDecimal amount, String type) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.amount = amount;
        this.type = type;
    }

    // Getters
    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    // Setters
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }
}
