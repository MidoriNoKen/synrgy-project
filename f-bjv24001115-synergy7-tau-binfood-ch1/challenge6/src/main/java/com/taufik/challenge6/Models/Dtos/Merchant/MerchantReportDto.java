package com.taufik.challenge6.Models.Dtos.Merchant;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.taufik.challenge6.Models.Dtos.Product.ProductIncomeDto;

@Data
public class MerchantReportDto {
    private UUID merchantId;
    private int totalProductQty;
    private int totalIncome;

    private List<ProductIncomeDto> totalProductIncome;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
