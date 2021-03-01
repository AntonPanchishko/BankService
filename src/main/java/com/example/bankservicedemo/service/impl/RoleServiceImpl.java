package com.example.bankservicedemo.service.impl;

import com.example.bankservicedemo.model.Role;
import com.example.bankservicedemo.repository.RoleRepository;
import com.example.bankservicedemo.service.RoleService;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role add(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getByName(String name) {
        return roleRepository.getByRoleName(Role.RoleType.valueOf(name))
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find role with such name " + name));
    }
}
