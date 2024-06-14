package com.taufik.challenge6.Models.Dtos.OrderDetail;

import lombok.Data;

@Data
public class OrderDetailReportDto {
    String productName;
    String size;
    int qty;
    int price;
}
