package com.example.bankservicedemo.controller;

import com.example.bankservicedemo.mapper.AccountMapper;
import com.example.bankservicedemo.mapper.TransactionMapper;
import com.example.bankservicedemo.model.dto.account.AccountRequestDto;
import com.example.bankservicedemo.model.dto.account.AccountResponseDto;
import com.example.bankservicedemo.model.dto.transaction.TransactionRequestDto;
import com.example.bankservicedemo.model.dto.transaction.TransactionResponseDto;
import com.example.bankservicedemo.service.AccountService;
import com.example.bankservicedemo.service.TransactionService;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final AccountMapper accountMapper;
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @PostMapping
    public void createAccount(@RequestBody AccountRequestDto accountRequestDto) {
        accountRequestDto.setActive(true);
        accountService.save(accountMapper.toEntity(accountRequestDto));
    }

    @GetMapping("/by-phone")
    public List<AccountResponseDto> getAccountByPhoneNumber(@RequestParam String phone) {
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

    @GetMapping("/history/{accountNumber}")
    public List<TransactionResponseDto> getHistory(
            @PathVariable String accountNumber,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {
        return transactionService.getAllByAccount(page, limit,
                accountService.getByAccountNumber(accountNumber))
                .stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/transfer")
    public String transfer(@RequestBody TransactionRequestDto requestDto) {
        transactionService.transfer(accountService.getByAccountNumber(requestDto.getToAccount()),
                accountService.getByAccountNumber(requestDto.getFromAccount()),
                BigDecimal.valueOf(requestDto.getAmount()));
        return "Payment is successful!";
    }
}
