package com.taufik.challenge5.Service.Impl;

import com.taufik.challenge5.Model.DTO.MerchantDTO;
import com.taufik.challenge5.Model.Entity.Merchant;
import com.taufik.challenge5.Repository.MerchantRepository;
import com.taufik.challenge5.Service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public List<MerchantDTO> list(Boolean open) {
        List<Merchant> merchants;
        if (open != null) {
            merchants = merchantRepository.findByOpen(open);
        } else {
            merchants = merchantRepository.findAll();
        }
        return merchants.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public MerchantDTO show(Long code) {
        Merchant merchant = merchantRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("Merchant not found"));

        return convertToDTO(merchant);
    }

    @Override
    @Transactional
    public MerchantDTO create(MerchantDTO merchantDTO) {
        Merchant merchant = new Merchant();
        merchant.setName(merchantDTO.getName());
        merchant.setLocation(merchantDTO.getLocation());
        merchant.setOpen(merchantDTO.getOpen());
        merchantRepository.save(merchant);
        return convertToDTO(merchant);
    }

    @Override
    @Transactional
    public MerchantDTO update(Long id, MerchantDTO merchantDTO) {
        Merchant merchant = merchantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Merchant not found"));
        merchant.setName(merchantDTO.getName());
        merchant.setLocation(merchantDTO.getLocation());
        merchant.setOpen(merchantDTO.getOpen());
        merchantRepository.save(merchant);
        return convertToDTO(merchant);
    }

    @Override
    @Transactional
    public Boolean delete(Long id) {
        Merchant merchant = merchantRepository.findById(id).orElse(null);
        if (merchant == null) return false;
        merchantRepository.delete(merchant);
        return true;
    }

    private MerchantDTO convertToDTO(Merchant merchant) {
        MerchantDTO dto = new MerchantDTO();
        dto.setCode(merchant.getCode());
        dto.setName(merchant.getName());
        dto.setLocation(merchant.getLocation());
        dto.setOpen(merchant.getOpen());
        return dto;
    }
}
