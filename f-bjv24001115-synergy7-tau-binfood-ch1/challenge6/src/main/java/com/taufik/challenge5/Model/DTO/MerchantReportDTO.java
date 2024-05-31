package com.taufik.challenge5.Model.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MerchantReportDTO {
    private String merchantName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal totalIncome;
}
