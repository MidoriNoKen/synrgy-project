package com.taufik.challenge6.Models.Dtos.User;

import lombok.Data;

import java.util.List;
import java.util.UUID;

import com.taufik.challenge6.Models.Entities.Auth.Role;

@Data
public class UserDto {
    private UUID id;

    private String username;
    private String email;
    private String password;
    private List<Role> roles;
}
