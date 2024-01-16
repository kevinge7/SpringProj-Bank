package com.chuwa.hw.bank_springboot.controller;

import com.chuwa.hw.bank_springboot.payload.BankStatementDto;
import com.chuwa.hw.bank_springboot.services.BankStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/bank-statements")
public class BankStatementController {
    @Autowired
    private BankStatementService bankStatementService;

    @GetMapping("/{accountId}")
    public ResponseEntity<BankStatementDto> generateBankStatement(@PathVariable Long accountId, @RequestParam LocalDate startDate,
                                                                  @RequestParam LocalDate endDate){
        BankStatementDto bankStatementDto = bankStatementService.generateBankStatement(accountId, startDate, endDate);
        return new ResponseEntity<>(bankStatementDto, HttpStatus.OK);
    }
}
