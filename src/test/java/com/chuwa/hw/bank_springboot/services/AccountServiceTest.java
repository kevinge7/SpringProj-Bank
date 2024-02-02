package com.chuwa.hw.bank_springboot.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import com.chuwa.hw.bank_springboot.dao.AccountRepository;
import com.chuwa.hw.bank_springboot.dao.UserProfileRepository;
import com.chuwa.hw.bank_springboot.entities.Account;
import com.chuwa.hw.bank_springboot.entities.UserProfile;
import com.chuwa.hw.bank_springboot.payload.AccountDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceTest.class);

    @Mock
    private AccountRepository accountRepositoryMock;

    @Mock
    private UserProfileRepository userProfileRepositoryMock;

    @InjectMocks
    private AccountService accountService;

    private Account account;
    private AccountDto accountDto;
    private UserProfile userProfile;

    @BeforeAll
    static void beforeAll(){logger.info("START Test");}

    @BeforeEach
    void setUp(){
        userProfile = new UserProfile();
        userProfile.setId(1L);

        account = new Account();
        account.setId(1L);
        account.setUserProfile(userProfile);
        account.setAccountNumber("12345");
        account.setRoutingNumber("67890");

        ModelMapper modelMapper = new ModelMapper();
        accountDto = modelMapper.map(account, AccountDto.class);
    }

    @Test
    void saveOrUpdateAccount() {
        Mockito.when(userProfileRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(userProfile));
        Mockito.when(accountRepositoryMock.save(ArgumentMatchers.any(Account.class))).thenReturn(account);

        Account savedAccount = accountService.saveOrUpdateAccount(accountDto);

        Assertions.assertNotNull(savedAccount);
        Assertions.assertEquals(account.getAccountNumber(), savedAccount.getAccountNumber());
        verify(accountRepositoryMock).save(any(Account.class));
    }

    @Test
    void getAccountById() {
        Mockito.when(accountRepositoryMock.findById(anyLong())).thenReturn(Optional.of(account));

        Account foundAccount = accountService.getAccountById(1L);

        Assertions.assertNotNull(foundAccount);
        Assertions.assertEquals(account.getId(), foundAccount.getId());
    }

    @Test
    void getAllAccountsByUserId() {
        when(accountRepositoryMock.findByUserProfileId(ArgumentMatchers.anyLong())).thenReturn(Arrays.asList(account));

        List<Account> accounts = accountService.getAllAccountsByUserId(1L);

        Assertions.assertFalse(accounts.isEmpty());
        Assertions.assertEquals(1, accounts.size());
        Assertions.assertEquals(account.getId(), accounts.get(0).getId());
    }

    @Test
    void deleteAccount() {
        Mockito.when(accountRepositoryMock.findById(anyLong())).thenReturn(Optional.of(account));
        Mockito.doNothing().when(accountRepositoryMock).delete(any(Account.class));

        Assertions.assertDoesNotThrow(() -> accountService.deleteAccount(1L));
        verify(accountRepositoryMock).delete(any(Account.class));
    }
}