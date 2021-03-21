package com.example.bankservicedemo.controller;

import com.example.bankservicedemo.mapper.UserMapper;
import com.example.bankservicedemo.model.User;
import com.example.bankservicedemo.model.dto.user.UserRequestDto;
import com.example.bankservicedemo.model.dto.user.UserResponseDto;
import com.example.bankservicedemo.service.RoleService;
import com.example.bankservicedemo.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final RoleService roleService;

    @PostMapping
    public UserResponseDto addNewUser(@RequestBody UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);
        user.getRoleSet().add(roleService.getByName("USER"));
        return userMapper.toDto(userService.create(user));
    }

    @PutMapping("/{userId}")
    public UserResponseDto updateUser(@RequestBody UserRequestDto userRequestDto,
                           @PathVariable Long userId) {
        User user = userMapper.toEntity(userRequestDto);
        user.setId(userId);
        user.setRoleSet(userService.findById(userId).getRoleSet());
        return userMapper.toDto(userService.create(user));
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUserById(@PathVariable Long userId) {
        return userMapper.toDto(userService.findById(userId));
    }

    @GetMapping("/by-phone")
    public UserResponseDto getUserByPhone(@RequestParam String phoneNumber) {
        return userMapper.toDto(userService.getByPhone(phoneNumber));
    }

    @DeleteMapping("/delete/user-id")
    public void deleteUser(@RequestParam Long userId) {
        userService.deleteById(userId);
    }

    @GetMapping
    public List<UserResponseDto> getAllUser() {
        return userService.findAll().stream()
                .map(userMapper::toDto).collect(Collectors.toList());
    }
}
