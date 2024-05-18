package com.taufik.challenge4.Service;

import com.taufik.challenge4.Model.DTO.MerchantDTO;

import java.util.List;

public interface MerchantService {
    void addMerchant(MerchantDTO merchantDTO);

    void updateMerchant(Long merchantCode, MerchantDTO merchantDTO);

    void changeMerchantStatus(Long merchantCode, boolean status);

    List<MerchantDTO> getOpenMerchants();

    MerchantDTO getMerchantById(Long merchantCode);
}
