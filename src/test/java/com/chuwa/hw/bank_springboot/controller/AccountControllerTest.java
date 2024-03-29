package com.chuwa.hw.bank_springboot.controller;

import com.chuwa.hw.bank_springboot.entities.Account;
import com.chuwa.hw.bank_springboot.entities.UserProfile;
import com.chuwa.hw.bank_springboot.payload.AccountDto;
import com.chuwa.hw.bank_springboot.services.impl.AccountServiceImpl;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(AccountControllerTest.class);

    @Mock
    private AccountServiceImpl accountServiceImpl;

    @InjectMocks
    AccountController accountController;

    private Account account;
    private AccountDto accountDto;
    private UserProfile userProfile;


    @BeforeAll
    static void beforeAll(){logger.info("START test");}

    @BeforeEach
    void setUp(){
        logger.info("set up Account for each test");
        userProfile = new UserProfile();
        userProfile.setId(1L); // 设置一个有效的ID

        this.account = new Account();
        account.setId(1L);
        account.setUserProfile(userProfile); // 确保设置了UserProfile
        account.setAccountNumber("12345");
        account.setRoutingNumber("67890");
        ModelMapper modelMapper = new ModelMapper();

        accountDto = new AccountDto();
        accountDto.setAccountId(1L);
        accountDto.setAccountNumber("123456789");
        accountDto.setRoutingNumber("987654321");
    }

    @Test
    void createOrUpdateAccount() {
        Mockito.when(accountServiceImpl.saveOrUpdateAccount(ArgumentMatchers.any(AccountDto.class))).thenReturn(account);
        // 这里结尾用getBody()的意思是 因为我accountController.createOrUpdateAccount返回的是一个ResponseEntity
        // 所以获取值的时候用getBody()
        ResponseEntity<AccountDto> response = accountController.createOrUpdateAccount(accountDto);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Mockito.verify(accountServiceImpl).saveOrUpdateAccount(ArgumentMatchers.any(AccountDto.class));
    }

    @Test
    void getAllAccounts() {
        Mockito.when(accountServiceImpl.getAllAccountsByUserId(ArgumentMatchers.anyLong())).thenReturn(Arrays.asList(account));
        ResponseEntity<List<AccountDto>> response = accountController.getAllAccounts(1L);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1, response.getBody().size());
        Mockito.verify(accountServiceImpl).getAllAccountsByUserId(ArgumentMatchers.anyLong());

    }

    @Test
    void deleteAccount() {
        Mockito.doNothing().when(accountServiceImpl).deleteAccount(ArgumentMatchers.anyLong());
        ResponseEntity<String> response = accountController.deleteAccount(1L);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Mockito.verify(accountServiceImpl).deleteAccount(ArgumentMatchers.anyLong());
    }
}