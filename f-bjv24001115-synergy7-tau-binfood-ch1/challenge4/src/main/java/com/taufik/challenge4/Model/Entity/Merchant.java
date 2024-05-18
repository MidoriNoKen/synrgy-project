package com.taufik.challenge4.Model.Entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Merchant")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long merchantCode;

    private String merchantName;
    private String merchantLocation;
    private boolean open;

    @OneToMany(mappedBy = "merchant")
    private List<Product> products;
}
