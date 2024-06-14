package com.taufik.challenge6.Services.Impl;

import com.taufik.challenge6.Exceptions.MerchantNotFoundException;
import com.taufik.challenge6.Models.Dtos.Merchant.*;
import com.taufik.challenge6.Models.Dtos.Product.ProductDto;
import com.taufik.challenge6.Models.Dtos.Product.ProductIncomeDto;
import com.taufik.challenge6.Models.Entities.*;
import com.taufik.challenge6.Models.Entities.Auth.User;
import com.taufik.challenge6.Repositories.MerchantRepository;
import com.taufik.challenge6.Services.ProductService;
import com.taufik.challenge6.Services.MerchantService;
import com.taufik.challenge6.Services.UserService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    MerchantRepository merchantRepository;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public MerchantDto create(MerchantCreateRequestDto merchantCreateRequestDto) {
        Merchant merchant = modelMapper.map(merchantCreateRequestDto, Merchant.class);

        merchant.setOwner(userService.getById(merchantCreateRequestDto.getOwnerId()));
        merchant.setOpen(true);

        merchant = merchantRepository.save(merchant);
        log.info("Merchant {} successfully added", merchant.getName());

        return modelMapper.map(merchant, MerchantDto.class);
    }

    @Override
    public Merchant getById(UUID id) {
        Optional<Merchant> optionalMerchant = merchantRepository.findById(id);
        if (optionalMerchant.isPresent()) {
            return optionalMerchant.get();
        }

        log.error("Merchant not found: {}", (id));
        throw new MerchantNotFoundException("Merchant not found: " + id);
    }

    @Override
    public MerchantDto getDtoById(UUID id) {
        return modelMapper.map(getById(id), MerchantDto.class);
    }

    @Override
    public List<MerchantDto> getList() {
        return merchantRepository.findAll().stream()
                .map(merchant -> modelMapper.map(merchant, MerchantDto.class))
                .toList();
    }

    @Override
    public List<MerchantDto> getOpenList() {
        return merchantRepository.findAll().stream()
                .filter(Merchant::isOpen)
                .map(merchant -> modelMapper.map(merchant, MerchantDto.class))
                .toList();
    }

    @Override
    public List<MerchantDto> getListByUser(User user) {
        return merchantRepository.findByOwner(user).stream()
                .map(merchant -> modelMapper.map(merchant, MerchantDto.class))
                .toList();
    }

    @Override
    public MerchantReportDto getReport(Merchant merchant, LocalDateTime startTime, LocalDateTime endTime) {
        MerchantReportDto merchantReportDto = new MerchantReportDto();
        merchantReportDto.setMerchantId(merchant.getId());
        merchantReportDto.setStartTime(startTime);
        merchantReportDto.setEndTime(endTime);

        List<ProductIncomeDto> totalProductIncome = new ArrayList<>();
        int totalProductQty = 0;
        int totalIncome = 0;

        for (ProductDto product : productService.getListByMerchant(merchant)) {
            ProductIncomeDto productIncome = new ProductIncomeDto();

            int qty = productService.getTotalQty(product.getId(), startTime, endTime);
            int price = productService.getTotalPrice(product.getId(), startTime, endTime);

            productIncome.setProductName(product.getName());
            productIncome.setQty(qty);
            productIncome.setTotalPrice(price);

            totalProductIncome.add(productIncome);
            totalProductQty += qty;
            totalIncome += price;
        }

        merchantReportDto.setTotalProductQty(totalProductQty);
        merchantReportDto.setTotalIncome(totalIncome);
        merchantReportDto.setTotalProductIncome(totalProductIncome);

        return merchantReportDto;
    }

    @Override
    public MerchantDto update(UUID id, MerchantUpdateRequestDto merchantUpdateRequestDto) {
        Merchant existingMerchant = getById(id);

        existingMerchant.setName(merchantUpdateRequestDto.getName());
        existingMerchant.setLocation(merchantUpdateRequestDto.getLocation());
        existingMerchant.setOpen(merchantUpdateRequestDto.isOpen());
        existingMerchant.setOwner(userService.getById(merchantUpdateRequestDto.getOwnerId()));

        Merchant updatedMerchant = merchantRepository.save(existingMerchant);
        return modelMapper.map(updatedMerchant, MerchantDto.class);
    }

    @Override
    public void safeDeleteById(UUID id) {
        Merchant merchant = getById(id);
        merchant.setDeleted(true);
        merchantRepository.save(merchant);
        log.info("Merchant {} has been deleted", id);
    }
}
