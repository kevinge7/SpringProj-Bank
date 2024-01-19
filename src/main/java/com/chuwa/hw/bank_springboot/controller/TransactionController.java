package com.chuwa.hw.bank_springboot.controller;

import com.chuwa.hw.bank_springboot.dao.AccountRepository;
import com.chuwa.hw.bank_springboot.dao.TransactionRepository;
import com.chuwa.hw.bank_springboot.entities.Account;
import com.chuwa.hw.bank_springboot.entities.Transaction;
import com.chuwa.hw.bank_springboot.payload.TransactionDto;
import com.chuwa.hw.bank_springboot.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
     @Autowired
     private AccountRepository accountRepository;

     @Autowired
    TransactionRepository transactionRepository;

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionDto>> getAllTransactionsByAccountId(@PathVariable Long accountId) {
        List<TransactionDto> transactions = transactionService.getAllTransactionsByAccountId(accountId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long id) {
        @Valid TransactionDto transaction = convertToDto(transactionService.getTransactionById(id));
        return ResponseEntity.ok(transaction);
    }

    @PostMapping
    public ResponseEntity<TransactionDto> saveOrUpdateTransaction(@RequestBody TransactionDto transactionDto) {
        Account account = accountRepository.findById(transactionDto.getAccountId())
                .orElseThrow(()-> new RuntimeException("Account not found"));
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setDate(transactionDto.getDate());
        transaction.setDescription(transactionDto.getDescription());
        transaction.setAmount(transactionDto.getAmount());
        Transaction savedTransaction = transactionRepository.save(transaction); // 保存实体

        return new ResponseEntity<>(convertToDto(savedTransaction), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok().build();
    }

    private TransactionDto convertToDto(Transaction transaction) {
        TransactionDto dto = new TransactionDto();
        dto.setAccountId(transaction.getAccount().getId());
        dto.setId(transaction.getId());
        dto.setDate(transaction.getDate());
        dto.setDescription(transaction.getDescription());
        dto.setAmount(transaction.getAmount());
        return dto;
    }

    private Transaction convertToEntity(TransactionDto dto) {
        Transaction transaction = new Transaction();
        Account account = accountRepository.findById(dto.getAccountId()).orElseThrow(()-> new RuntimeException("Account not found"));
        transaction.setId(dto.getId());
        transaction.setAccount(account);
        transaction.setDate(dto.getDate());
        transaction.setDescription(dto.getDescription());
        transaction.setAmount(dto.getAmount());
        return transaction;
    }

}
