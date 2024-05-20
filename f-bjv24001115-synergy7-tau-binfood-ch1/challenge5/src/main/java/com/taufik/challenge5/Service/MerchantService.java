package com.taufik.challenge5.Service;

import com.taufik.challenge5.Model.DTO.MerchantDTO;

import java.util.List;

public interface MerchantService {

    List<MerchantDTO> list(Boolean open);

    MerchantDTO show(Long id);

    void create(MerchantDTO merchantDTO);

    void update(Long id, MerchantDTO merchantDTO);

    void delete(Long id);
}
