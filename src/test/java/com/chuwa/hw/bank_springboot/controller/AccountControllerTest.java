package com.chuwa.hw.bank_springboot.controller;

import com.chuwa.hw.bank_springboot.entities.Account;
import com.chuwa.hw.bank_springboot.payload.AccountDto;
import com.chuwa.hw.bank_springboot.services.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(AccountControllerTest.class);

    @Mock
    private AccountService accountService;

    @InjectMocks
    AccountController accountController;

    private Account account;
    private AccountDto accountDto;

    @BeforeAll
    static void beforeAll(){logger.info("START test");}

    @BeforeEach
    void setUp(){
        logger.info("set up Account for each test");

        this.account = new Account();
        account.setId(1L);
        account.setAccountNumber("12345");
        account.setRoutingNumber("67890");
        ModelMapper modelMapper = new ModelMapper();
        this.accountDto = modelMapper.map(this.account, AccountDto.class);

    }

    @Test
    void createOrUpdateAccount() {
        Mockito.when(accountService.saveOrUpdateAccount(ArgumentMatchers.any(AccountDto.class))).thenReturn(account);
        // 这里结尾用getBody()的意思是 因为我accountController.createOrUpdateAccount返回的是一个ResponseEntity
        // 所以获取值的时候用getBody()
        AccountDto responseDto = accountController.createOrUpdateAccount(accountDto).getBody();


        Assertions.assertNotNull(responseDto, "responseDto is Null");
        assertEquals(accountDto.getAccountId(), responseDto.getAccountId(), "Account ID does not match");
        assertEquals(accountDto.getUserId(), responseDto.getUserId(), "User ID does not match");
        assertEquals(accountDto.getAccountNumber(), responseDto.getAccountNumber(), "Account number does not match");
        assertEquals(accountDto.getRoutingNumber(), responseDto.getRoutingNumber(), "Routing number does not match");
    }

//    @Test
//    void getAllAccounts() {
//    }
//
//    @Test
//    void deleteAccount() {
//    }
}