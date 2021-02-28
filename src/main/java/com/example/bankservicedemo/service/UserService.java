package com.example.bankservicedemo.service;

import com.example.bankservicedemo.model.User;

public interface UserService {
    User create(User user);

    User update(User user);

    User get(Long id);

    User getByPhone(String phone);

    void remove(Long id);
}
