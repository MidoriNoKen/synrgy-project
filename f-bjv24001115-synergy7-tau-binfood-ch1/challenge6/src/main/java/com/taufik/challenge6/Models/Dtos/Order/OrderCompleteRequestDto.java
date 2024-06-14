package com.taufik.challenge6.Models.Dtos.Order;

import lombok.Data;

@Data
public class OrderCompleteRequestDto {
    private String destinationAddress;
    private String notes;
}
