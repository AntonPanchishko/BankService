package com.example.bankservicedemo.repository;

import com.example.bankservicedemo.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> getByRoleName(Role.RoleType name);
}
