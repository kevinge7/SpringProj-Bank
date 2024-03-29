package com.chuwa.hw.bank_springboot.Resolver;

import com.chuwa.hw.bank_springboot.entities.Account;
import com.chuwa.hw.bank_springboot.entities.Transaction;
import com.chuwa.hw.bank_springboot.services.impl.AccountServiceImpl;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

@Component
public class TransactionResolver implements GraphQLResolver<Transaction> {
    private final AccountServiceImpl accountServiceImpl;

    public TransactionResolver(AccountServiceImpl accountServiceImpl) {
        this.accountServiceImpl = accountServiceImpl;
    }

    public Account account(Transaction transaction) {
        return accountServiceImpl.getAccountById(transaction.getAccount().getId());
    }
}

