package com.poc.bank.service;

import com.poc.bank.entity.Card;
import com.poc.bank.respository.CardRepository;
import com.poc.bank.util.PinHashUtil;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.Optional;


@Service
public class CardService {


    private final CardRepository cardRepository;


    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    public Optional<Card> findByCardNumber(String cardNumber) {
        return cardRepository.findById(cardNumber);
    }


    public boolean verifyPin(String cardNumber, String pin) {
        return cardRepository.findById(cardNumber)
                .map(card -> card.getHashedPin().equals(PinHashUtil.sha256(pin)))
                .orElse(false);
    }


    public Card save(Card card) { return cardRepository.save(card); }


    public Card updateBalance(String cardNumber, BigDecimal newBalance) {
        Card c = cardRepository.findById(cardNumber).orElseThrow();
        c.setBalance(newBalance);
        return cardRepository.save(c);
    }

    public BigDecimal getBalance(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber)
                .map(card -> card.getBalance())
                .orElse(BigDecimal.ZERO);
    }

}
