package com.example.bankservicedemo.service.impl;

import com.example.bankservicedemo.model.User;
import com.example.bankservicedemo.repository.UserRepository;
import com.example.bankservicedemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User getByPhone(String phone) {
        return userRepository.getFirstByPhoneNumber(phone).get();
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }
}
