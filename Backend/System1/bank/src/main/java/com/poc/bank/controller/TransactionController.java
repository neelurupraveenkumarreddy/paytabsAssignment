package com.poc.bank.controller;

import com.poc.bank.client.CoreBankClient;
import com.poc.bank.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gateway")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TransactionController {

    private final CoreBankClient bankClient;

    @PostMapping("/transaction")
    public ResponseEntity<?> handleTransaction(@RequestBody TransactionRequest req) {

        // 1️⃣ Routing: only cards starting with '4' allowed
        if (!req.getCardNumber().startsWith("4")) {
            return ResponseEntity.badRequest()
                    .body("Routing Failed: Unsupported card (System-1)");
        }

        // 2️⃣ Verify PIN via System-2
        boolean validPin = bankClient.verifyPin(
                new VerifyPinRequest(req.getCardNumber(), req.getPin())
        );
        if (!validPin) {
            return ResponseEntity.status(401).body("Invalid PIN");
        }

        // 3️⃣ Process the transaction via System-2
        TransactionResponse response = bankClient.processTransaction(req);
        try {
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // return realistic error message from System-2
            return ResponseEntity.badRequest().body("Transaction Failed: " + e.getMessage());
        }
    }

    @GetMapping("/balance/{cardNumber}")
    public ResponseEntity<BalanceResponse> getBalance(@PathVariable String cardNumber) {
        return ResponseEntity.ok(bankClient.getBalance(cardNumber));
    }
    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> all() {
        return ResponseEntity.ok(bankClient.getAllTransactions());
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<List<Transaction>> byId(@PathVariable Long id) {
        return ResponseEntity.ok(bankClient.getTransactionById(id));
    }

    @GetMapping("/transactions/card/{cardNumber}")
    public ResponseEntity<List<Transaction>> byCard(@PathVariable String cardNumber) {
        return ResponseEntity.ok(bankClient.getTransactionByCardNumber(cardNumber));
    }
}
