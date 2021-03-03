package com.example.bankservicedemo.service.impl;

import com.example.bankservicedemo.exception.NoEnoughMoneyException;
import com.example.bankservicedemo.model.Account;
import com.example.bankservicedemo.model.Transaction;
import com.example.bankservicedemo.repository.TransactionRepository;
import com.example.bankservicedemo.service.AccountService;
import com.example.bankservicedemo.service.ClientService;
import com.example.bankservicedemo.service.TransactionService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final ClientService clientService;

    @Transactional
    @Override
    public void transfer(Account fromAccount, Account toAccount, BigDecimal amount) {
        Transaction transactionOutcoming = new Transaction();
        if (Double.parseDouble(String.valueOf(fromAccount.getBalance()
                .subtract(amount))) <= 0) {
            throw new NoEnoughMoneyException("No enough money on balance");
        }
        transactionOutcoming.setAccountFrom(fromAccount);
        transactionOutcoming.setAccountTo(toAccount);
        transactionOutcoming.setAmount(amount);
        transactionOutcoming.setDate(LocalDateTime.now());
        transactionOutcoming.setType(Transaction.Type.OUTCOMING);
        fromAccount.setBalance(fromAccount.getBalance()
                .subtract(amount));
        accountService.save(fromAccount);
        transactionRepository.save(transactionOutcoming);

        Transaction transactionIncoming = new Transaction();
        transactionIncoming.setAccountFrom(fromAccount);
        transactionIncoming.setAccountTo(toAccount);
        transactionIncoming.setAmount(amount);
        transactionIncoming.setDate(LocalDateTime.now());
        transactionIncoming.setType(Transaction.Type.INCOMING);
        if (fromAccount.getCurrency() != toAccount.getCurrency()) {
            double rate = clientService.getRate(LocalDate.now(),
                    fromAccount.getCurrency(), toAccount.getCurrency());
            amount = BigDecimal.valueOf(Double.parseDouble(String.valueOf(amount)) * rate);
        }
        toAccount.setBalance(toAccount.getBalance()
                .add(amount));
        accountService.save(toAccount);
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
