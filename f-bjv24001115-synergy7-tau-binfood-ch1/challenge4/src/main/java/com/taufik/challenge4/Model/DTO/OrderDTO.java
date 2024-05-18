package com.taufik.challenge4.Model.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private LocalDateTime orderTime;
    private String destinationAddress;
    private boolean completed;
    private Long userId;
}
