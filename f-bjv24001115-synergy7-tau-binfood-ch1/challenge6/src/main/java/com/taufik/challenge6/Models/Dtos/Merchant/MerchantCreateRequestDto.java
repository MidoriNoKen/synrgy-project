package com.taufik.challenge6.Models.Dtos.Merchant;

import lombok.Data;

import java.util.UUID;

@Data
public class MerchantCreateRequestDto {
    private String name;
    private String location;
    private UUID ownerId;
}
