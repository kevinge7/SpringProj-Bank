package com.chuwa.hw.bank_springboot.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
public class BankStatementDto {

    @NotEmpty
    private Long accountId;

    @NotEmpty
    private LocalDate startDate;

    @NotEmpty
    private LocalDate endDate;

    private AccountDto accountDto;

    private UserProfileDto userProfileDto;

    private List<TransactionDto> transactions;


    public BankStatementDto() {
    }

    public BankStatementDto(Long accountId, LocalDate startDate, LocalDate endDate, List<TransactionDto> transactions) {
        this.accountId = accountId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.transactions = transactions;
    }
}
