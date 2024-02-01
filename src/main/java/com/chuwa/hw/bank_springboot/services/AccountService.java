package com.chuwa.hw.bank_springboot.services;

import com.chuwa.hw.bank_springboot.dao.AccountRepository;
import com.chuwa.hw.bank_springboot.dao.UserProfileRepository;
import com.chuwa.hw.bank_springboot.entities.Account;
import com.chuwa.hw.bank_springboot.entities.UserProfile;
import com.chuwa.hw.bank_springboot.payload.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    AccountRepository accountRepository;

    public Account saveOrUpdateAccount(AccountDto accountDto){
        UserProfile userProfile = userProfileRepository.findById(accountDto.getUserId())
                .orElseThrow(()-> new RuntimeException("UserProfile not found"));
        Account account = new Account();
        account.setUserProfile(userProfile);
        account.setRoutingNumber(accountDto.getRoutingNumber());
        account.setAccountNumber(accountDto.getAccountNumber());
        return accountRepository.save(account);
    }
    public Account getAccountById(Long id){
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found for this id :: " + id));
    }
    public List<Account> getAllAccountsByUserId(Long userId) {
        return accountRepository.findByUserProfileId(userId);
    }

    // Delete an account
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found for this id :: " + id));
        accountRepository.delete(account);
    }

}
