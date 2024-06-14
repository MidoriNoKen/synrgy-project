package com.taufik.challenge6.Models.Dtos.Product;

import lombok.Data;

import java.util.UUID;

import com.taufik.challenge6.Models.Entities.Product;

@Data
public class ProductCreateRequestDto {
    private String name;
    private Product.Type type;
    private Integer priceS;
    private Integer priceM;
    private Integer priceL;
    private UUID merchantId;
}
