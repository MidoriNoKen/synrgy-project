package com.taufik.challenge4.Model.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String username;
    private String emailAddress;
    private String password;
}
