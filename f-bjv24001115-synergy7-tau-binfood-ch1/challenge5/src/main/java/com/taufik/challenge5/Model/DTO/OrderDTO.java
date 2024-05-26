package com.taufik.challenge5.Model.DTO;

import lombok.Data;

import java.time.LocalDateTime;

import com.taufik.challenge5.Model.Entity.Merchant;
import com.taufik.challenge5.Model.Entity.User;

@Data
public class OrderDTO {
    private Long id;
    private LocalDateTime date;
    private String address;
    private User user;
    private Merchant merchant;
    private Boolean completed;
}
