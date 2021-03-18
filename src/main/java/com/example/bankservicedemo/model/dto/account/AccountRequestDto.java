package com.example.bankservicedemo.model.dto.account;

import lombok.Data;

@Data
public class AccountRequestDto {
    private String accountNumber;
    private String currency;
    private Double balance;
    private boolean isActive;
}
