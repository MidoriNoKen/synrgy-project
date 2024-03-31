package com.taufik.models.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class Cashier {
    private User user;
    private Product product;
    private Payment payment;
    private double totalItem, totalPrice;
}