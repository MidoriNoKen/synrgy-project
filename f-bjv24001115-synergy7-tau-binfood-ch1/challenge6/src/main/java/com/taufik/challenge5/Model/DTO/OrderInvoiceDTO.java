package com.taufik.challenge5.Model.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderInvoiceDTO {
    private Boolean completed;
    private BigDecimal price;
    private Integer quantity;
    private LocalDateTime date;
    private String address;
    private String username;
    private String email;
}
