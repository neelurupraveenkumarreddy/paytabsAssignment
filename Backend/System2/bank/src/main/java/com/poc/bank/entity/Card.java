package com.poc.bank.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "cards")
public class Card {


    @Id
    @Column(name = "card_number", nullable = false)
    private String cardNumber;


    @Column(name = "hashed_pin", nullable = false)
    private String hashedPin;


    @Column(name = "customer_name")
    private String customerName;


    @Column(name = "balance", nullable = false)
    private BigDecimal balance;


    public Card() {}


    public Card(String cardNumber, String hashedPin, String customerName, BigDecimal balance) {
        this.cardNumber = cardNumber;
        this.hashedPin = hashedPin;
        this.customerName = customerName;
        this.balance = balance;
    }


    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }


    public String getHashedPin() { return hashedPin; }
    public void setHashedPin(String hashedPin) { this.hashedPin = hashedPin; }


    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }


    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}