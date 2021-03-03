package com.example.bankservicedemo.service;

import com.example.bankservicedemo.model.Account;
import com.example.bankservicedemo.model.Transaction;
import java.util.List;

public interface TransactionService {
    void transfer(Account fromAccount, Account toAccount, int amount);

    List<Transaction> getAllByAccount(int page, int size, Account account);
}
