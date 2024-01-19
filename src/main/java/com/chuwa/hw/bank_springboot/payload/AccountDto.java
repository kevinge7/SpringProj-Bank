package com.chuwa.hw.bank_springboot.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {
    private Long accountId;

    private Long userId;

    @NotEmpty(message = "Routing Number should not be empty. ")
    private String routingNumber;

    @NotEmpty(message = "Account Number should not be empty. ")
    private String accountNumber;


    public AccountDto(Long userId, String routingNumber, String accountNumber) {
        this.userId = userId;
        this.routingNumber = routingNumber;
        this.accountNumber = accountNumber;
    }

    public AccountDto() {
    }


}
