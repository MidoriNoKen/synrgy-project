package com.taufik.challenge5.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "merchant_code", nullable = false)
    private Merchant merchant;

    @Column(name = "completed", nullable = false)
    private Boolean completed = false;

    @Column(name = "quantity", nullable = true)
    private int quantity = 0;

    @Column(name = "price", nullable = true)
    private BigDecimal price = BigDecimal.ZERO;

    public boolean isCompleted() {
        return completed;
    }
}
