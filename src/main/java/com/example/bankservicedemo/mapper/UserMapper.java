package com.example.bankservicedemo.mapper;

import com.example.bankservicedemo.model.Role;
import com.example.bankservicedemo.model.User;
import com.example.bankservicedemo.model.dto.user.UserRequestDto;
import com.example.bankservicedemo.model.dto.user.UserResponseDto;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRequestDto userRequestDto) {
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setPhoneNumber(userRequestDto.getPhoneNumber());
        user.setPassword(userRequestDto.getPassword());
        Role role = new Role();
        role.setRoleName(Role.RoleType.valueOf(userRequestDto.getRole()));
        Set<Role> roleSet = user.getRoleSet();
        roleSet.add(role);
        user.setRoleSet(roleSet);
        return user;
    }

    public UserResponseDto toDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setDateOfBirth(user.getDateOfBirth().toString());
        userResponseDto.setPhoneNumber(user.getPhoneNumber());
        userResponseDto.setRole(user.getRoleSet().toString());
        return userResponseDto;
    }
}
