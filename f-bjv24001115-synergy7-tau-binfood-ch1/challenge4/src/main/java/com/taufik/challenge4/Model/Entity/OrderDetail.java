package com.taufik.challenge4.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order_id;

    @ManyToOne
    @JoinColumn(name = "product_code")
    private Product product_code;

    private int quantity;
    private Double totalPrice;
}