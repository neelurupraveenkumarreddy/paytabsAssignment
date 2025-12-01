package com.poc.bank.client;


import com.poc.bank.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "core-bank", url = "http://localhost:8081/internal")
public interface CoreBankClient {

    // Verify PIN
    @PostMapping("/cards/verify-pin")
    boolean verifyPin(@RequestBody VerifyPinRequest request);
    //get balance
    @GetMapping("/cards/{cardNumber}/balance")
    BalanceResponse getBalance(@PathVariable("cardNumber") String cardNumber);

    // Process a transaction (TOPUP or PURCHASE)
    @PostMapping("/transactions/process")
    TransactionResponse processTransaction(@RequestBody TransactionRequest request);

    @GetMapping("/transactions")
    List<Transaction> getAllTransactions();

    @GetMapping("/transactions/{id}")
    List<Transaction> getTransactionById(@PathVariable("id") Long id);

    @GetMapping("/transactions/card/{cardNumber}")
    List<Transaction> getTransactionByCardNumber(@PathVariable("cardNumber") String cardNumber);

}
