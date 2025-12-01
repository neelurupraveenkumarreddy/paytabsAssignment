package com.poc.bank.controller;

import com.poc.bank.dto.BalanceResponse;
import com.poc.bank.dto.TransactionRequest;
import com.poc.bank.dto.TransactionResponse;
import com.poc.bank.dto.VerifyPinRequest;
import com.poc.bank.entity.Transaction;
import com.poc.bank.respository.TransactionRepository;
import com.poc.bank.service.CardService;
import com.poc.bank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/internal")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProcessingController {


    private final CardService cardService;
    private final TransactionService transactionService;
    private final TransactionRepository txRepo;


    @PostMapping("/cards/verify-pin")
    public ResponseEntity<?> verifyPin(@RequestBody VerifyPinRequest req) {
        boolean ok = cardService.verifyPin(req.getCardNumber(), req.getPin());
        return ResponseEntity.ok(ok);
    }

    @GetMapping("/cards/{cardNumber}/balance")
    public ResponseEntity<BalanceResponse> getBalance(@PathVariable String cardNumber) {
        BigDecimal balance = cardService.getBalance(cardNumber);
        return ResponseEntity.ok(new BalanceResponse(balance));
    }


    @PostMapping("/transactions/process")
    public ResponseEntity<TransactionResponse> process(@RequestBody TransactionRequest req) {
        TransactionResponse res = transactionService.process(req);
        if ("OK".equals(res.getStatus())) return ResponseEntity.ok(res);
        return ResponseEntity.badRequest().body(res);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> all() {
        return ResponseEntity.ok(txRepo.findAll());
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<List<Transaction>> getTransactionsById(@PathVariable Long id) {
        List<Transaction> list = txRepo.findById(id)
                .map(List::of)
                .orElse(List.of()); // return empty list if not found
        return ResponseEntity.ok(list);
    }

    @GetMapping("/transactions/card/{cardNumber}")
    public ResponseEntity<List<Transaction>> getTransactionsByCard(
            @PathVariable String cardNumber
    ) {
        List<Transaction> list = txRepo.findByCardNumber(cardNumber);
        return ResponseEntity.ok(list);
    }

}