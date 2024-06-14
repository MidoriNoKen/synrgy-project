package com.taufik.challenge6.Models.Dtos.User;

import lombok.Data;

@Data
public class UserUpdateRequestDto {
    private String username;
    private String email;
    private String password;
}
