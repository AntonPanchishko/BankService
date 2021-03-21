package com.example.bankservicedemo.mapper;

import com.example.bankservicedemo.model.User;
import com.example.bankservicedemo.model.dto.user.UserRequestDto;
import com.example.bankservicedemo.model.dto.user.UserResponseDto;
import java.time.LocalDate;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRequestDto userRequestDto) {
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setDateOfBirth(LocalDate.parse(userRequestDto.getDateOfBirth()));
        user.setPassword(userRequestDto.getPassword());
        user.setPhoneNumber(userRequestDto.getPhoneNumber());
        return user;

    }

    public UserResponseDto toDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setDateOfBirth(user.getDateOfBirth().toString());
        userResponseDto.setPhoneNumber(user.getPhoneNumber());
        userResponseDto.setRole(user.getRoleSet()
                .stream()
                .map(String::valueOf)
                .collect(Collectors.toList()));
        return userResponseDto;
    }
}
