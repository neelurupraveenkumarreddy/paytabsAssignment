package com.poc.bank.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceResponse {
    private BigDecimal balance;
}
