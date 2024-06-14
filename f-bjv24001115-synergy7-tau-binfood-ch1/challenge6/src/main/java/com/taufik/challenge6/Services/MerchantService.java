package com.taufik.challenge6.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.taufik.challenge6.Models.Dtos.Merchant.*;
import com.taufik.challenge6.Models.Entities.*;
import com.taufik.challenge6.Models.Entities.Auth.User;

public interface MerchantService {
    // CREATE
    MerchantDto create(MerchantCreateRequestDto merchantCreateRequestDto);

    // READ
    Merchant getById(UUID id);

    MerchantDto getDtoById(UUID id);

    List<MerchantDto> getList();

    List<MerchantDto> getOpenList();

    List<MerchantDto> getListByUser(User user);

    MerchantReportDto getReport(Merchant merchant, LocalDateTime startTime, LocalDateTime endTime);

    // UPDATE
    MerchantDto update(UUID id, MerchantUpdateRequestDto merchantUpdateRequestDto);

    // DELETE
    void safeDeleteById(UUID id);
}
