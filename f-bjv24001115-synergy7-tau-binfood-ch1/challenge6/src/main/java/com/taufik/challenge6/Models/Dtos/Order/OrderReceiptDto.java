package com.taufik.challenge6.Models.Dtos.Order;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.taufik.challenge6.Models.Dtos.OrderDetail.OrderDetailReportDto;

@Data
public class OrderReceiptDto {
    UUID orderId;
    LocalDateTime orderTime;
    String merchantName;
    String destinationAddress;
    List<OrderDetailReportDto> orderDetailReportDtoList;
    int totalQty;
    int totalPrice;
}
