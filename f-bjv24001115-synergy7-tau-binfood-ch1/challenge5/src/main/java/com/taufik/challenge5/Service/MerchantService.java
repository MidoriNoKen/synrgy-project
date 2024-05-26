package com.taufik.challenge5.Service;

import com.taufik.challenge5.Model.DTO.MerchantDTO;

import java.util.List;

public interface MerchantService {

    List<MerchantDTO> list(Boolean open);

    MerchantDTO show(Long id);

    MerchantDTO create(MerchantDTO merchantDTO);

    MerchantDTO update(Long id, MerchantDTO merchantDTO);

    Boolean delete(Long id);
}
