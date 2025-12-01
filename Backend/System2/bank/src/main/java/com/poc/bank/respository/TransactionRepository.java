package com.poc.bank.respository;


import com.poc.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCardNumberOrderByTimestampDesc(String cardNumber);
    List<Transaction> findByCardNumber(String cardNumber);

}