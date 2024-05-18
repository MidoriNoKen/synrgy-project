package com.taufik.challenge4.Service;

import com.taufik.challenge4.Model.DTO.MerchantDTO;
import com.taufik.challenge4.Model.Entity.Merchant;
import com.taufik.challenge4.Repository.MerchantRepository;
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
    @Transactional
    public void addMerchant(MerchantDTO merchantDTO) {
        Merchant merchant = new Merchant();
        merchant.setMerchantName(merchantDTO.getMerchantName());
        merchant.setMerchantLocation(merchantDTO.getMerchantLocation());
        merchant.setOpen(merchantDTO.isOpen());

        merchantRepository.save(merchant);
    }

    @Override
    @Transactional
    public void updateMerchant(Long merchantCode, MerchantDTO merchantDTO) {
        Merchant merchant = merchantRepository.findById(merchantCode)
                .orElseThrow(() -> new IllegalArgumentException("Merchant not found"));

        merchant.setMerchantName(merchantDTO.getMerchantName());
        merchant.setMerchantLocation(merchantDTO.getMerchantLocation());
        merchant.setOpen(merchantDTO.isOpen());

        merchantRepository.save(merchant);
    }

    @Override
    @Transactional
    public void changeMerchantStatus(Long merchantCode, boolean status) {
        Merchant merchant = merchantRepository.findById(merchantCode)
                .orElseThrow(() -> new IllegalArgumentException("Merchant not found"));

        merchant.setOpen(status);

        merchantRepository.save(merchant);
    }

    @Override
    public List<MerchantDTO> getOpenMerchants() {
        List<Merchant> openMerchants = merchantRepository.findByOpen(true);
        return openMerchants.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public MerchantDTO getMerchantById(Long merchantCode) {
        Merchant merchant = merchantRepository.findById(merchantCode)
                .orElseThrow(() -> new IllegalArgumentException("Merchant not found"));
        return mapToDTO(merchant);
    }

    private MerchantDTO mapToDTO(Merchant merchant) {
        MerchantDTO dto = new MerchantDTO();
        dto.setMerchantCode(merchant.getMerchantCode());
        dto.setMerchantName(merchant.getMerchantName());
        dto.setMerchantLocation(merchant.getMerchantLocation());
        dto.setOpen(merchant.isOpen());
        return dto;
    }
}
