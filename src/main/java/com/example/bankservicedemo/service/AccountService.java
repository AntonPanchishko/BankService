package com.example.bankservicedemo.service;

import com.example.bankservicedemo.model.Account;
import java.util.List;

public interface AccountService {
    Account save(Account account);

    List<Account> getAccountsByPhone(String phone);

    Account getByAccountNumber(String accountNumber);
}
