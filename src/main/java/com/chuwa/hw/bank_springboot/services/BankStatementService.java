package com.chuwa.hw.bank_springboot.services;
import com.chuwa.hw.bank_springboot.dao.AccountRepository;
import com.chuwa.hw.bank_springboot.dao.TransactionRepository;
import com.chuwa.hw.bank_springboot.dao.UserProfileRepository;
import com.chuwa.hw.bank_springboot.entities.Account;
import com.chuwa.hw.bank_springboot.entities.Transaction;
import com.chuwa.hw.bank_springboot.payload.AccountDto;
import com.chuwa.hw.bank_springboot.payload.BankStatementDto;

import com.chuwa.hw.bank_springboot.payload.TransactionDto;
import com.chuwa.hw.bank_springboot.payload.UserProfileDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class BankStatementService {
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public BankStatementDto generateBankStatement(Long accountId, LocalDate startDate, LocalDate endDate){
        Account account = accountRepository.findById(accountId).orElseThrow(()-> new RuntimeException("Account not found for this id :: " + accountId));
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        UserProfileDto userProfileDto = modelMapper.map(account.getUserProfile(), UserProfileDto.class);
        List<Transaction> transactions = transactionRepository.findAllByAccountIdAndDateBetween(accountId, startDate, endDate);
        BankStatementDto response = new BankStatementDto();
        response.setAccountId(account.getId());
        response.setStartDate(startDate);
        response.setEndDate(endDate);
        response.setTransactions(transactions.stream().map(this::mapToTransactionResponse).collect(Collectors.toList()));
        response.setAccountDto(accountDto);
        response.setUserProfileDto(userProfileDto);
        return  response;
    }

    private TransactionDto mapToTransactionResponse(Transaction transaction) {
        TransactionDto response = new TransactionDto();
        response.setDate(transaction.getDate());
        response.setDescription(transaction.getDescription());
        response.setAmount(transaction.getAmount());
        return response;
    }
}
