package com.taufik.challenge6.Models.Dtos.OrderDetail;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderDetailDto {
    private UUID id;

    private String size;
    private int qty;
    private int price;
    private UUID productId;
    private UUID orderId;
}
