package com.chuwa.hw.bank_springboot.payload;

import java.time.LocalDate;
import java.util.List;

public class BankStatementDto {
    private Long accountId;
    private LocalDate startDate;
    private LocalDate endDate;

    private List<TransactionDto> transactions;




    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BankStatementDto() {
    }

    public BankStatementDto(Long accountId, LocalDate startDate, LocalDate endDate, List<TransactionDto> transactions) {
        this.accountId = accountId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.transactions = transactions;
    }

    public List<TransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDto> transactions) {
        this.transactions = transactions;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

}
