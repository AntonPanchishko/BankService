package com.example.bankservicedemo.controller;

import com.example.bankservicedemo.model.Account;
import com.example.bankservicedemo.model.Currency;
import com.example.bankservicedemo.model.Role;
import com.example.bankservicedemo.model.User;
import com.example.bankservicedemo.service.AccountService;
import com.example.bankservicedemo.service.RoleService;
import com.example.bankservicedemo.service.TransactionService;
import com.example.bankservicedemo.service.UserService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insert")
@AllArgsConstructor
public class DataInsert {
    private final RoleService roleService;
    private final UserService userService;
    private final AccountService accountService;
    private final TransactionService transactionService;

    @GetMapping
    private void init() {
        Role user = new Role();
        user.setRoleName(Role.RoleType.USER);
        roleService.add(user);
        Role admin = new Role();
        admin.setRoleName(Role.RoleType.ADMIN);
        roleService.add(admin);

        User bob = new User();
        bob.setName("Bob");
        bob.setDateOfBirth(LocalDate.of(2000,01,01));
        bob.setRoleSet(Set.of(admin, user));
        bob.setPassword("pass1");
        bob.setPhoneNumber("0112223344");
        userService.create(bob);

        User alice = new User();
        alice.setName("Alice");
        alice.setDateOfBirth(LocalDate.of(2000,02,02));
        alice.setRoleSet(Set.of(admin, user));
        alice.setPassword("pass2");
        alice.setPhoneNumber("0556667788");
        userService.create(alice);

        Account accountBob = new Account();
        accountBob.setBalance(BigDecimal.valueOf(1000));
        accountBob.setActive(true);
        accountBob.setAccountNumber("1111 2222 3333 4444");
        accountBob.setCurrency(Currency.USD);
        accountBob.setUser(bob);
        accountService.save(accountBob);

        Account accountAlice = new Account();
        accountAlice.setBalance(BigDecimal.valueOf(2000));
        accountAlice.setActive(true);
        accountAlice.setAccountNumber("5555 6666 7777 8888");
        accountAlice.setCurrency(Currency.EUR);
        accountAlice.setUser(alice);
        accountService.save(accountAlice);

        transactionService.transfer(accountBob, accountAlice, 100);
        System.out.println("transaction successfully");
    }
}
