package com.taufik.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Payment {
    private String paymentMethod;
    private long nominal;
}
