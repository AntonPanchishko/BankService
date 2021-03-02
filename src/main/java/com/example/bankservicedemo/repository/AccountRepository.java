package com.example.bankservicedemo.repository;

import com.example.bankservicedemo.model.Account;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM accounts a JOIN FETCH a.user u WHERE u.phoneNumber = :phone")
    List<Account> getAccountByPhone(String phone);

    Optional<Account> findByAccountNumber(String accountNumber);
}
