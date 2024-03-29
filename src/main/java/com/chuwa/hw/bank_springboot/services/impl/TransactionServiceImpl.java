package com.chuwa.hw.bank_springboot.services.impl;

import com.chuwa.hw.bank_springboot.dao.TransactionRepository;
import com.chuwa.hw.bank_springboot.entities.Transaction;
import com.chuwa.hw.bank_springboot.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction saveOrUpdateTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Long id){
        return transactionRepository.findById(id).orElseThrow(()-> new RuntimeException("Transaction not found for this id :: " + id));

    }

    @Override
    public List<Transaction> getAllTransactionByAccountId(Long accountId){
        return transactionRepository.findByAccountId(accountId);
    }

    @Override
    public void deleteTransaction(Long id){
        Transaction transaction = transactionRepository.findById(id).orElseThrow(()-> new RuntimeException("Transaction not found for this id :: " + id));
        transactionRepository.delete(transaction);
    }

    @Override
    public List<Transaction> getAllTransactionsByAccountId(Long accountId) {
        return transactionRepository.getAllTransactionsByAccountId(accountId);
    }
}
