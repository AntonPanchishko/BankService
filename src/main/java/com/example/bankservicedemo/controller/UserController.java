package com.example.bankservicedemo.controller;

import com.example.bankservicedemo.mapper.UserMapper;
import com.example.bankservicedemo.model.dto.user.UserRequestDto;
import com.example.bankservicedemo.model.dto.user.UserResponseDto;
import com.example.bankservicedemo.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/add")
    public void addNewUser(UserRequestDto userRequestDto) {
        userService.create(userMapper.toEntity(userRequestDto));
    }

    @GetMapping("/update")
    public void updateUser(UserRequestDto userRequestDto) {
        userService.update(userMapper.toEntity(userRequestDto));
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUserById(@PathVariable Long userId) {
        return userMapper.toDto(userService.getById(userId));
    }

    @GetMapping("/{phoneNumber}")
    public UserResponseDto getUserByPhone(@PathVariable String phoneNumber) {
        return userMapper.toDto(userService.getByPhone(phoneNumber));
    }

    @GetMapping("/delete/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteById(userId);
    }

    @GetMapping
    public List<UserResponseDto> getAllUser() {
        return userService.findAll().stream()
                .map(userMapper::toDto).collect(Collectors.toList());
    }
}
