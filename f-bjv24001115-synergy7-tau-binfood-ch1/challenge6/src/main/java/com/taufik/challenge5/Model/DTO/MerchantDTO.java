package com.taufik.challenge5.Model.DTO;

import lombok.Data;

@Data
public class MerchantDTO {
    private Long code;
    private String name;
    private String location;
    private Boolean open;
}
