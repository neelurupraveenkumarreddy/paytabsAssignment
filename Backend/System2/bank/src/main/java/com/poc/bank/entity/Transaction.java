package com.poc.bank.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "transactions")
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "card_number", nullable = false)
    private String cardNumber;


    @Column(name = "amount", nullable = false)
    private BigDecimal amount;


    @Column(name = "type", nullable = false)
    private String type; // TOPUP or PURCHASE


    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;


    public Transaction() {}


    public Transaction(String cardNumber, BigDecimal amount, String type) {
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }


    public Long getId() { return id; }
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}