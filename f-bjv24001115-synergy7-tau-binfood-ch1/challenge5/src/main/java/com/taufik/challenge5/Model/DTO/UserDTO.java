package com.taufik.challenge5.Model.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
}
