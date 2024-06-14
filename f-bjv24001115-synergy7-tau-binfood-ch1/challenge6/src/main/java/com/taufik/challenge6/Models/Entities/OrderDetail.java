package com.taufik.challenge6.Models.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_detail")
@SQLDelete(sql = "update order_detail set deleted = true where id =?")
@SQLRestriction("deleted = false")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String size;

    private int qty;

    private int price;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "menu_item_id")
    private Product product;

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id")
    private Order order;

    private boolean deleted;

    public void addQty(int qty) {
        this.qty += qty;
    }
}
