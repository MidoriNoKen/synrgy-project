package com.taufik.challenge4.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCode;

    private String productName;
    private double price;

    @ManyToOne
    @JoinColumn(name = "merchant_code")
    private Merchant merchant;
}
