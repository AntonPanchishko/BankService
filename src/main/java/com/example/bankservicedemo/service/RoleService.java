package com.example.bankservicedemo.service;

import com.example.bankservicedemo.model.Role;
import java.util.List;

public interface RoleService {
    public Role add(Role role);

    public Role getByName(String name);

    public List<Role> findAll();
}
