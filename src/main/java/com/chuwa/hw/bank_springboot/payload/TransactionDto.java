package com.chuwa.hw.bank_springboot.payload;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter

public class TransactionDto {
    private Long id;

    private Long accountId;

    private LocalDate date;

    @NotEmpty
    @Size(min = 2, message = "Description is too short.")
    private String description;
    @NotEmpty
    private BigDecimal amount;
}
