package com.chuwa.hw.bank_springboot.services;

import com.chuwa.hw.bank_springboot.entities.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction saveOrUpdateTransaction(Transaction transaction);

    Transaction getTransactionById(Long id);

    List<Transaction> getAllTransactionByAccountId(Long accountId);

    void deleteTransaction(Long id);

    List<Transaction> getAllTransactionsByAccountId(Long accountId);
}
