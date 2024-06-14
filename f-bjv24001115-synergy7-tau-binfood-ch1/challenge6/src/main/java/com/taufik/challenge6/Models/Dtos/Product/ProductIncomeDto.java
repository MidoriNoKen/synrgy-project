package com.taufik.challenge6.Models.Dtos.Product;

import lombok.Data;

@Data
public class ProductIncomeDto {
    private String productName;
    private int qty;
    private int totalPrice;
}
