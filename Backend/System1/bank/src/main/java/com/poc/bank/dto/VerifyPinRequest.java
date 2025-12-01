package com.poc.bank.dto;

public class VerifyPinRequest {

    private String cardNumber;
    private String pin;

    public VerifyPinRequest() {}

    public VerifyPinRequest(String cardNumber, String pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    // Getters
    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    // Setters
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
