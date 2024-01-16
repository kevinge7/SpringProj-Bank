package com.chuwa.hw.bank_springboot.dao;

import com.chuwa.hw.bank_springboot.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountId(Long accountId);

    List<Transaction> findAllByAccountIdAndDateBetween(Long accountId, LocalDate startDate, LocalDate endDate);

    List<Transaction> getAllTransactionsByAccountId(Long accountId);
}
