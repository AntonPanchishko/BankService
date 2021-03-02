package com.example.bankservicedemo.controller;

import com.example.bankservicedemo.model.Account;
import com.example.bankservicedemo.model.Currency;
import com.example.bankservicedemo.model.Role;
import com.example.bankservicedemo.model.User;
import com.example.bankservicedemo.service.AccountService;
import com.example.bankservicedemo.service.RoleService;
import com.example.bankservicedemo.service.UserService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import javax.annotation.PostConstruct;
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

    @GetMapping
    @PostConstruct
    private void init() {
        Role user = new Role();
        user.setRoleName(Role.RoleType.USER);
        roleService.add(user);
        Role admin = new Role();
        admin.setRoleName(Role.RoleType.ADMIN);
        roleService.add(admin);

        User adminUser = new User();
        adminUser.setName("Admin");
        adminUser.setDateOfBirth(LocalDate.of(2000,01,01));
        adminUser.setRoleSet(Set.of(admin, user));
        adminUser.setPassword("pass1word");
        adminUser.setPhoneNumber("0678534856");
        userService.create(adminUser);

        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(1000));
        account.setActive(true);
        account.setAccountNumber("1111 2222 3333 4444");
        account.setCurrency(Currency.USD);
        account.setUser(adminUser);
        accountService.save(account);

        Account byAccountNumber = accountService.getByAccountNumber("1111 2222 3333 4444");
        byAccountNumber.setActive(false);
        accountService.save(byAccountNumber);
    }
}
