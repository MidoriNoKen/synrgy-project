package com.taufik.challenge4.Model.DTO;

import lombok.Data;

@Data
public class MerchantDTO {
    private Long merchantCode;
    private String merchantName;
    private String merchantLocation;
    private boolean open;
}
