package com.poc.bank.service;

import com.poc.bank.dto.TransactionRequest;
import com.poc.bank.dto.TransactionResponse;
import com.poc.bank.entity.Transaction;
import com.poc.bank.respository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;


@Service
public class TransactionService {


    private final TransactionRepository txRepo;
    private final CardService cardService;


    public TransactionService(TransactionRepository txRepo, CardService cardService) {
        this.txRepo = txRepo;
        this.cardService = cardService;
    }


    @Transactional
    public TransactionResponse process(TransactionRequest req) {
// verify card exists
        var opt = cardService.findByCardNumber(req.getCardNumber());
        if (opt.isEmpty()) {
            return new TransactionResponse("FAIL", "Card not found", null, null);
        }


        var card = opt.get();


// verify pin
        if (!cardService.verifyPin(req.getCardNumber(), req.getPin())) {
            return new TransactionResponse("FAIL", "Invalid PIN", card.getBalance(), null);
        }


        BigDecimal newBalance = card.getBalance();
        if ("TOPUP".equalsIgnoreCase(req.getType())) {
            newBalance = newBalance.add(req.getAmount());
        } else if ("PURCHASE".equalsIgnoreCase(req.getType())) {
            if (card.getBalance().compareTo(req.getAmount()) < 0) {
                return new TransactionResponse("FAIL", "Insufficient funds", card.getBalance(), null);
            }
            newBalance = newBalance.subtract(req.getAmount());
        } else {
            return new TransactionResponse("FAIL", "Unknown transaction type", card.getBalance(), null);
        }


// update balance and save transaction
        card.setBalance(newBalance);
        cardService.save(card);


        Transaction tx = new Transaction(card.getCardNumber(), req.getAmount(), req.getType());
        tx = txRepo.save(tx);


        return new TransactionResponse("OK", "Success", newBalance, tx.getId());
    }
}