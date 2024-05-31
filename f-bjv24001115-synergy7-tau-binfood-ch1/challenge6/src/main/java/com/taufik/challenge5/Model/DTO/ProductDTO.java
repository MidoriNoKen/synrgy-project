package com.taufik.challenge5.Model.DTO;

import java.math.BigDecimal;

import com.taufik.challenge5.Model.Entity.Merchant;

import lombok.Data;

@Data
public class ProductDTO {
    private Long code;
    private String name;
    private BigDecimal price;
    private Merchant merchant;
}
