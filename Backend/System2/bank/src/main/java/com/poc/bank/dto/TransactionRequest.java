package com.poc.bank.dto;

import java.math.BigDecimal;


public class TransactionRequest {
    private String cardNumber;
    private String pin; // plain pin for demo; better to use hashed pin
    private BigDecimal amount;
    private String type; // TOPUP or PURCHASE


    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}