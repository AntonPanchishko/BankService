package com.example.bankservicedemo.service.impl;

import com.example.bankservicedemo.exception.NoSuchEntityException;
import com.example.bankservicedemo.model.Account;
import com.example.bankservicedemo.repository.AccountRepository;
import com.example.bankservicedemo.service.AccountService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAccountsByPhone(String phone) {
        return accountRepository.getAccountByPhone(phone);
    }

    @Override
    public Account getByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow(()
                -> {
            throw new NoSuchEntityException("Can't find account"
                    + " with such account number");
        });
    }

    @Override
    public void blockAccount(String accountNumber) {
        Account byAccountNumber = getByAccountNumber(accountNumber);
        byAccountNumber.setActive(false);
        accountRepository.save(byAccountNumber);
    }
}
