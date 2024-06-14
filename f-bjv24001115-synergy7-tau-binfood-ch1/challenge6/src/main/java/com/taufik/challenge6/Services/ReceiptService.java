package com.taufik.challenge6.Services;

import java.util.UUID;

import com.taufik.challenge6.Models.Dtos.Order.OrderReceiptDto;

public interface ReceiptService {
    OrderReceiptDto getOrderReceiptDto(UUID orderId);
}
