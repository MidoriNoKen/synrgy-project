package com.taufik.challenge6.Models.Dtos.OrderDetail;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderDetailCreateRequestDto {
    private String size;
    private int qty;
    private UUID orderId;
    private UUID productId;
}
