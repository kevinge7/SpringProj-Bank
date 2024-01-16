package com.chuwa.hw.bank_springboot.services;

import com.chuwa.hw.bank_springboot.dao.TransactionRepository;
import com.chuwa.hw.bank_springboot.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction saveOrUpdateTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(Long id){
        return transactionRepository.findById(id).orElseThrow(()-> new RuntimeException("Transaction not found for this id :: " + id));

    }

    public List<Transaction> getAllTransactionByAccountId(Long accountId){
        return transactionRepository.findByAccountId(accountId);
    }

    public void deleteTransaction(Long id){
        Transaction transaction = transactionRepository.findById(id).orElseThrow(()-> new RuntimeException("Transaction not found for this id :: " + id));
        transactionRepository.delete(transaction);
    }

    public List<Transaction> getAllTransactionsByAccountId(Long accountId) {
        return transactionRepository.getAllTransactionsByAccountId(accountId);
    }
}
