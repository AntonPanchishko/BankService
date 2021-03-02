package com.example.bankservicedemo.service.impl;

import com.example.bankservicedemo.exception.NoEnoughMoneyException;
import com.example.bankservicedemo.model.Account;
import com.example.bankservicedemo.model.Transaction;
import com.example.bankservicedemo.repository.TransactionRepository;
import com.example.bankservicedemo.service.AccountService;
import com.example.bankservicedemo.service.TransactionService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Override
    public void transfer(String fromAccount, String toAccount, int amount) {
        Transaction transactionOutcoming = new Transaction();
        Account fromAccountByNumber = accountService.getByAccountNumber(fromAccount);
        if (Double.parseDouble(String.valueOf(transactionOutcoming
                .getAmount().subtract(BigDecimal.valueOf(amount)))) <= 0) {
            throw new NoEnoughMoneyException("No enough money on balance");
        }
        Account toAccountNyNumber = accountService.getByAccountNumber(toAccount);
        transactionOutcoming.setAccountFrom(fromAccountByNumber);
        transactionOutcoming.setAccountTo(toAccountNyNumber);
        transactionOutcoming.setAmount(BigDecimal.valueOf(amount));
        transactionOutcoming.setDate(LocalDateTime.now());
        transactionOutcoming.setType(Transaction.Type.OUTCOMING);
        fromAccountByNumber.setBalance(fromAccountByNumber
                .getBalance().subtract(BigDecimal.valueOf(amount)));
        accountService.save(fromAccountByNumber);
        transactionRepository.save(transactionOutcoming);

        Transaction transactionIncoming = new Transaction();
        transactionIncoming.setAccountFrom(fromAccountByNumber);
        transactionIncoming.setAccountTo(toAccountNyNumber);
        transactionIncoming.setAmount(BigDecimal.valueOf(amount));
        transactionIncoming.setDate(LocalDateTime.now());
        transactionIncoming.setType(Transaction.Type.INCOMING);
        toAccountNyNumber.setBalance(toAccountNyNumber
                .getBalance().add(BigDecimal.valueOf(amount)));
        accountService.save(toAccountNyNumber);
        transactionRepository.save(transactionIncoming);
    }

    @Override
    public List<Transaction> getAllByAccount(int page, int size, Account account) {
        PageRequest sortedByDateAndId =
                PageRequest.of(page, size, Sort.by("date").descending().and(Sort.by("id")));
        return transactionRepository.findAllByAccount(account
                .getAccountNumber(), sortedByDateAndId);
    }
}
