package com.example.bankservicedemo.service.impl;

import com.example.bankservicedemo.exception.NoSuchEntityException;
import com.example.bankservicedemo.model.User;
import com.example.bankservicedemo.repository.UserRepository;
import com.example.bankservicedemo.service.UserService;
import java.util.List;
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
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("Cant find user with such id" + id));
    }

    @Override
    public User getByPhone(String phone) {
        return userRepository.getFirstByPhoneNumber(phone)
                .orElseThrow(() ->
                        new NoSuchEntityException("Can't find user with such phone " + phone));
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
