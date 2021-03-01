package com.example.bankservicedemo.repository;

import com.example.bankservicedemo.model.Transaction;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM transactions t JOIN FETCH t.accountFrom af"
            + " JOIN FETCH t.accountTo at"
            + " WHERE af.accountNumber = :accountNumber OR"
            + " at.accountNumber = :accountNumber")
    List<Transaction> findAllByAccount(String accountNumber, Pageable sortedByDateAndId);
}
