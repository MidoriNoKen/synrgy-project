package com.taufik.challenge5.Model.DTO;

import java.math.BigDecimal;

import com.taufik.challenge5.Model.Entity.Order;
import com.taufik.challenge5.Model.Entity.Product;

import lombok.Data;

@Data
public class OrderDetailDTO {
    private Long id;
    private Order order;
    private Product product;
    private Long quantity;
    private BigDecimal totalPrice;
}
