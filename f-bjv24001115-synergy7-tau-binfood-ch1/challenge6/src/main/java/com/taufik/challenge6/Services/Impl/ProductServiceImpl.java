package com.taufik.challenge6.Services.Impl;

import com.taufik.challenge6.Exceptions.ProductNameExistedException;
import com.taufik.challenge6.Exceptions.ProductNotFoundException;
import com.taufik.challenge6.Models.Dtos.Product.ProductCreateRequestDto;
import com.taufik.challenge6.Models.Dtos.Product.ProductDto;
import com.taufik.challenge6.Models.Dtos.Product.ProductUpdateRequestDto;
import com.taufik.challenge6.Models.Entities.*;
import com.taufik.challenge6.Repositories.ProductRepository;
import com.taufik.challenge6.Services.ProductService;
import com.taufik.challenge6.Services.MerchantService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    MerchantService merchantService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ProductDto create(ProductCreateRequestDto productCreateRequestDto) {
        Product product = modelMapper.map(productCreateRequestDto, Product.class);

        product.setMerchant(merchantService.getById(productCreateRequestDto.getMerchantId()));

        if (productRepository.existsByMerchantAndName(product.getMerchant(), product.getName())) {
            log.error("Name existed: {}", product.getName());
            throw new ProductNameExistedException("Name existed: " + product.getName());
        }

        product = productRepository.save(product);
        log.info("Menu item {} successfully added", product.getName());

        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public Product getById(UUID id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }

        log.error("Menu item not found: {}", (id));
        throw new ProductNotFoundException("Menu item not found: " + id);
    }

    @Override
    public ProductDto getDtoById(UUID id) {
        return modelMapper.map(getById(id), ProductDto.class);
    }

    @Override
    public List<ProductDto> getListByMerchant(Merchant merchant) {
        return productRepository.findByMerchant(merchant).stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    @Override
    public List<ProductDto> getListByMerchantAndPage(Merchant merchant, int page, int productCountPerPage) {
        Pageable pageable = PageRequest.of(page, productCountPerPage);
        return productRepository.findByMerchant(merchant, pageable).stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    @Override
    public List<String> getAvailableSize(Product product) {
        ArrayList<String> availableSize = new ArrayList<>();

        availableSize.add("M");
        if (product.getPriceS() != null)
            availableSize.add("S");
        if (product.getPriceL() != null)
            availableSize.add("L");

        return availableSize;
    }

    @Override
    public int getTotalPage(Merchant merchant, int productCountPerPage) {
        return (int) Math.ceil((double) getListByMerchant(merchant).size() / productCountPerPage);
    }

    @Override
    public int getTotalPrice(UUID id, LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null) {
            startTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        }

        if (endTime == null) {
            endTime = LocalDateTime.now();
        }

        log.info("Start: {}", startTime);
        log.info("End: {}", endTime);

        return Optional.ofNullable(productRepository.getTotalPrice(id, startTime, endTime)).orElse(0);
    }

    @Override
    public int getTotalQty(UUID id, LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null) {
            startTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        }

        if (endTime == null) {
            endTime = LocalDateTime.now();
        }

        return Optional.ofNullable(productRepository.getTotalQty(id, startTime, endTime)).orElse(0);
    }

    @Override
    public ProductDto update(UUID id, ProductUpdateRequestDto productUpdateRequestDto) {
        Product existingProduct = getById(id);

        existingProduct.setName(productUpdateRequestDto.getName());
        existingProduct.setType(productUpdateRequestDto.getType());
        existingProduct.setPriceS(productUpdateRequestDto.getPriceS());
        existingProduct.setPriceM(productUpdateRequestDto.getPriceM());
        existingProduct.setPriceL(productUpdateRequestDto.getPriceL());

        Product updatedProduct = productRepository.save(existingProduct);
        return modelMapper.map(updatedProduct, ProductDto.class);
    }

    @Override
    public void safeDeleteById(UUID id) {
        Product product = getById(id);
        product.setDeleted(true);
        productRepository.save(product);
        log.info("Menu item {} has been deleted", id);
    }
}
