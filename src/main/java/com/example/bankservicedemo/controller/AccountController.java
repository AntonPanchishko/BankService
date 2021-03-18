package com.example.bankservicedemo.controller;

import com.example.bankservicedemo.mapper.AccountMapper;
import com.example.bankservicedemo.model.dto.account.AccountRequestDto;
import com.example.bankservicedemo.model.dto.account.AccountResponseDto;
import com.example.bankservicedemo.service.AccountService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @PostMapping("/add")
    public void addAccount(AccountRequestDto accountRequestDto) {
        accountService.save(accountMapper.toEntity(accountRequestDto));
    }

    @GetMapping("/{phone}")
    public List<AccountResponseDto> getAccountByPhoneNumber(@PathVariable String phone) {
        return accountService
                .getAccountsByPhone(phone)
                .stream()
                .map(accountMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{number}")
    public AccountResponseDto getAccountByAccountNumber(@PathVariable String number) {
        return accountMapper.toDto(accountService.getByAccountNumber(number));
    }
}
