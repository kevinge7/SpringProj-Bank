package com.chuwa.hw.bank_springboot.Resolver;

import com.chuwa.hw.bank_springboot.entities.Account;
import com.chuwa.hw.bank_springboot.entities.UserProfile;
import com.chuwa.hw.bank_springboot.services.impl.AccountServiceImpl;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserProfileResolver implements GraphQLResolver<UserProfile> {
    private final AccountServiceImpl accountServiceImpl;

    public UserProfileResolver(AccountServiceImpl accountServiceImpl) {
        this.accountServiceImpl = accountServiceImpl;
    }

    public List<Account> accounts(UserProfile userProfile) {
        return accountServiceImpl.getAllAccountsByUserId(userProfile.getId());
    }
}

