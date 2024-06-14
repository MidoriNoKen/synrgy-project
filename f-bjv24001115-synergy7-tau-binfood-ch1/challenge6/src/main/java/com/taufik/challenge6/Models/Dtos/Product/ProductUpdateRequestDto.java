package com.taufik.challenge6.Models.Dtos.Product;

import com.taufik.challenge6.Models.Entities.Product;

import lombok.Data;

@Data
public class ProductUpdateRequestDto {
    private String name;
    private Product.Type type;
    private Integer priceS;
    private Integer priceM;
    private Integer priceL;
}
