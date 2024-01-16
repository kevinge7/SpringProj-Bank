package com.chuwa.hw.bank_springboot.payload;

public class AccountDto {
    private Long accountId;

    public Long getAccountId() {
        return accountId;
    }

    public AccountDto(Long userId, String routingNumber, String accountNumber) {
        this.userId = userId;
        this.routingNumber = routingNumber;
        this.accountNumber = accountNumber;
    }

    public AccountDto() {
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    private Long userId;
    private String routingNumber;
    private String accountNumber;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
