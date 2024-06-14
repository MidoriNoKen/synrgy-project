package com.taufik.challenge6.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.taufik.challenge6.Models.Dtos.Product.ProductCreateRequestDto;
import com.taufik.challenge6.Models.Dtos.Product.ProductDto;
import com.taufik.challenge6.Models.Dtos.Product.ProductUpdateRequestDto;
import com.taufik.challenge6.Models.Entities.*;

public interface ProductService {
    // CREATE
    ProductDto create(ProductCreateRequestDto productCreateRequestDto);

    // READ
    Product getById(UUID id);

    ProductDto getDtoById(UUID id);

    List<ProductDto> getListByMerchant(Merchant merchant);

    List<ProductDto> getListByMerchantAndPage(Merchant merchant, int page, int productPerPage);

    List<String> getAvailableSize(Product product);

    int getTotalPage(Merchant merchant, int productCountPerPage);

    int getTotalPrice(UUID id, LocalDateTime startTime, LocalDateTime endTime);

    int getTotalQty(UUID id, LocalDateTime startTime, LocalDateTime endTime);

    // UPDATE
    ProductDto update(UUID id, ProductUpdateRequestDto productUpdateRequestDto);

    // DELETE
    void safeDeleteById(UUID id);
}
