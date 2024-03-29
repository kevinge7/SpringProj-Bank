package com.chuwa.hw.bank_springboot.Resolver;

import com.chuwa.hw.bank_springboot.entities.Account;
import com.chuwa.hw.bank_springboot.entities.Transaction;
import com.chuwa.hw.bank_springboot.entities.UserProfile;
import com.chuwa.hw.bank_springboot.services.impl.AccountServiceImpl;
import com.chuwa.hw.bank_springboot.services.impl.TransactionServiceImpl;
import com.chuwa.hw.bank_springboot.services.impl.UserProfileServiceImpl;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class QueryResolver implements GraphQLQueryResolver {
    private final AccountServiceImpl accountServiceImpl;
    private final UserProfileServiceImpl userProfileServiceImpl;
    private final TransactionServiceImpl transactionServiceImpl;

    public QueryResolver(AccountServiceImpl accountServiceImpl, UserProfileServiceImpl userProfileServiceImpl, TransactionServiceImpl transactionServiceImpl) {
        this.accountServiceImpl = accountServiceImpl;
        this.userProfileServiceImpl = userProfileServiceImpl;
        this.transactionServiceImpl = transactionServiceImpl;
    }

    public Account accountById(Long id) {
        return accountServiceImpl.getAccountById(id);
    }

    public UserProfile userProfileById(Long id) {
        return userProfileServiceImpl.getUserProfileById(id);
    }

    public Transaction transactionById(Long id) {
        return transactionServiceImpl.getTransactionById(id);
    }
}
