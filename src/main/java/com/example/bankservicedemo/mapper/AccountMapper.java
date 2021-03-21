package com.example.bankservicedemo.mapper;

import com.example.bankservicedemo.model.Account;
import com.example.bankservicedemo.model.Currency;
import com.example.bankservicedemo.model.dto.account.AccountRequestDto;
import com.example.bankservicedemo.model.dto.account.AccountResponseDto;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account toEntity(AccountRequestDto accountRequestDto) {
        Account account = new Account();
        account.setAccountNumber(accountRequestDto.getAccountNumber());
        account.setCurrency(Currency.valueOf(accountRequestDto.getCurrency()));
        account.setBalance(BigDecimal.valueOf(accountRequestDto.getBalance()));
        return account;
    }

    public AccountResponseDto toDto(Account account) {
        AccountResponseDto accountResponseDto = new AccountResponseDto();
        accountResponseDto.setAccountNumber(account.getAccountNumber());
        accountResponseDto.setActive(account.isActive());
        accountResponseDto.setBalance(account.getBalance().toString());
        accountResponseDto.setCurrency(account.getCurrency().toString());
        accountResponseDto.setId(account.getId());
        accountResponseDto.setUserId(account.getUser().getId());
        return accountResponseDto;
    }
}
