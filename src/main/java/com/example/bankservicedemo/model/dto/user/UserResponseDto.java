package com.example.bankservicedemo.model.dto.user;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String dateOfBirth;
    private String phoneNumber;
    private String password;
    private String role;
}
