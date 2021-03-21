package com.example.bankservicedemo.model.dto.account;

import lombok.Data;

@Data
public class AccountResponseDto {
    private Long id;
    private String accountNumber;
    private String currency;
    private String balance;
    private boolean isActive;
    private Long userId;
}
