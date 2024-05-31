package com.taufik.challenge5.Service;

import com.taufik.challenge5.Model.DTO.MerchantDTO;
import com.taufik.challenge5.Model.DTO.MerchantReportDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface MerchantService {

    List<MerchantDTO> list(Boolean open);

    MerchantDTO show(Long id);

    MerchantDTO create(MerchantDTO merchantDTO);

    MerchantDTO update(Long id, MerchantDTO merchantDTO);

    boolean delete(Long id);

    MerchantReportDTO generateReport(Long merchantCode, LocalDateTime startDate, LocalDateTime endDate);
}
