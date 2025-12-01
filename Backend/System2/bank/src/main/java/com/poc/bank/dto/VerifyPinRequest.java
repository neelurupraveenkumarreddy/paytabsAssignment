package com.poc.bank.dto;


public class VerifyPinRequest {
    private String cardNumber;
    private String pin; // Accept plain PIN for now (System-1 should avoid sending plain PIN)


    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }
}