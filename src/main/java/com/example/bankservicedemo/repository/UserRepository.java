package com.example.bankservicedemo.repository;

import com.example.bankservicedemo.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.roleSet WHERE u.phoneNumber = ?1")
    Optional<User> getFirstByPhoneNumber(String phoneNumber);

    @Override
    @Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.roleSet WHERE u.id = ?1")
    Optional<User> findById(Long id);
}
