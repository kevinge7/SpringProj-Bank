package com.chuwa.hw.bank_springboot.services;

import com.chuwa.hw.bank_springboot.entities.Account;
import com.chuwa.hw.bank_springboot.payload.AccountDto;

import java.util.List;

public interface AccountService {
    Account saveOrUpdateAccount(AccountDto accountDto);
    Account getAccountById(Long id);
    List<Account> getAllAccountsByUserId(Long userId);

    // Delete an account
    void deleteAccount(Long id);
}
