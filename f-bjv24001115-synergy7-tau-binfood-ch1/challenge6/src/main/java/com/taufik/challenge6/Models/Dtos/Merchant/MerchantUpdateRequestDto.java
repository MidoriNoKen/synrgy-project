package com.taufik.challenge6.Models.Dtos.Merchant;

import lombok.Data;

import java.util.UUID;

@Data
public class MerchantUpdateRequestDto {
    private String name;
    private String location;
    private boolean open;
    private UUID ownerId;
}
