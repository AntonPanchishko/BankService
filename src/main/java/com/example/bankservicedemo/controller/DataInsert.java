package com.example.bankservicedemo.controller;

import com.example.bankservicedemo.model.Role;
import com.example.bankservicedemo.model.User;
import com.example.bankservicedemo.service.RoleService;
import com.example.bankservicedemo.service.UserService;
import java.time.LocalDate;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInsert {
    private final RoleService roleService;
    private final UserService userService;

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
    }
}
