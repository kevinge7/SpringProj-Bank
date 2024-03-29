package com.chuwa.hw.bank_springboot.services;

import com.chuwa.hw.bank_springboot.entities.Transaction;
import com.chuwa.hw.bank_springboot.payload.BankStatementDto;
import com.chuwa.hw.bank_springboot.payload.TransactionDto;

import java.time.LocalDate;

public interface BankStatementService {
    BankStatementDto generateBankStatement(Long accountId, LocalDate startDate, LocalDate endDate);

    TransactionDto mapToTransactionResponse(Transaction transaction);
}
