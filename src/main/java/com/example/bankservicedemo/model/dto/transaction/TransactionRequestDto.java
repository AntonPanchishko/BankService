package com.example.bankservicedemo.model.dto.transaction;

import lombok.Data;

@Data
public class TransactionRequestDto {
    private String fromAccount;
    private String toAccount;
    private Double amount;
}
