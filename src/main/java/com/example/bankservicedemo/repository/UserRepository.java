package com.example.bankservicedemo.repository;

import com.example.bankservicedemo.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getFirstByPhoneNumber(String phoneNumber);

    Optional<User> getFirstById(Long id);
}
