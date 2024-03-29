package com.chuwa.hw.bank_springboot.Resolver;

import com.chuwa.hw.bank_springboot.entities.Account;
import com.chuwa.hw.bank_springboot.entities.Transaction;
import com.chuwa.hw.bank_springboot.entities.UserProfile;
import com.chuwa.hw.bank_springboot.services.impl.TransactionServiceImpl;
import com.chuwa.hw.bank_springboot.services.impl.UserProfileServiceImpl;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountResolver implements GraphQLResolver<Account> {
    private final TransactionServiceImpl transactionServiceImpl;
    private final UserProfileServiceImpl userProfileServiceImpl;
    @Autowired
    public AccountResolver(TransactionServiceImpl transactionServiceImpl, UserProfileServiceImpl userProfileServiceImpl) {
        this.transactionServiceImpl = transactionServiceImpl;
        this.userProfileServiceImpl = userProfileServiceImpl;
    }

    public UserProfile userProfile(Account account) {
        return userProfileServiceImpl.getUserProfileById(account.getUserProfile().getId());
    }

    public List<Transaction> transactions(Account account) {
        return transactionServiceImpl.getAllTransactionsByAccountId(account.getId());
    }
}

