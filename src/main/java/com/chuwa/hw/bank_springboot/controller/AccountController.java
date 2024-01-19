package com.chuwa.hw.bank_springboot.controller;

import com.chuwa.hw.bank_springboot.entities.Account;
import com.chuwa.hw.bank_springboot.payload.AccountDto;
import com.chuwa.hw.bank_springboot.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDto> createOrUpdateAccount(@Valid @RequestBody AccountDto accountDto){
        AccountDto savedAccount = convertToDto(accountService.saveOrUpdateAccount(accountDto));
        return new ResponseEntity<>(savedAccount, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<AccountDto>> getAllAccounts(@PathVariable Long id){
            List<AccountDto> accounts = accountService.getAllAccountsByUserId(id).stream()
                    .map(this::convertToDto).collect(Collectors.toList());
            return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return new ResponseEntity<>("Account Successfully Deleted!", HttpStatus.OK);
    }

    private AccountDto convertToDto(Account account){
        AccountDto dto = new AccountDto();
        dto.setUserId(account.getUserProfile().getId());
        dto.setAccountId(account.getId());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setRoutingNumber(account.getRoutingNumber());
        return dto;
    }

    private Account convertToEntity(AccountDto accountDto){
        Account account = new Account();
        account.setId(accountDto.getAccountId());
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setRoutingNumber(account.getRoutingNumber());
        return account;
    }
}
