package com.example.bankservicedemo.mapper;

import com.example.bankservicedemo.model.Transaction;
import com.example.bankservicedemo.model.dto.transaction.TransactionResponseDto;

public class TransactionMapper {
    public TransactionResponseDto toDto(Transaction transaction) {
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        transactionResponseDto.setAmount(String.valueOf(transaction.getAmount()));
        transactionResponseDto.setDateTime(transaction.getDate().toString());
        transactionResponseDto.setType(transaction.getType().toString());
        transactionResponseDto.setFromAccount(transaction.getAccountFrom().getAccountNumber());
        transactionResponseDto.setToAccount(transaction.getAccountTo().getAccountNumber());
        transactionResponseDto.setId(transaction.getId());
        return transactionResponseDto;
    }
}
