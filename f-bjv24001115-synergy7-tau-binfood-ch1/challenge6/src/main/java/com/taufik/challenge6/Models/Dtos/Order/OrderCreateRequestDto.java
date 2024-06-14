package com.taufik.challenge6.Models.Dtos.Order;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderCreateRequestDto {
    private UUID userId;
    private UUID merchantId;
}
