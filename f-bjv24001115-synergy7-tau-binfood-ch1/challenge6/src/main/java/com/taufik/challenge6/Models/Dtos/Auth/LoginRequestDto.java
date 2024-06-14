package com.taufik.challenge6.Models.Dtos.Auth;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String username;
    private String password;
}
