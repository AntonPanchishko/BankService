package com.example.bankservicedemo.service;

import com.example.bankservicedemo.model.User;
import java.util.List;

public interface UserService {
    User create(User user);

    User update(User user);

    User findById(Long id);

    User getByPhone(String phone);

    void remove(Long id);

    List<User> findAll();

    User getById(Long userId);

    void deleteById(Long id);
}
