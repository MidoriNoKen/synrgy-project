package com.taufik.challenge4.Service;

import java.util.List;

import com.taufik.challenge4.Model.DTO.ProductDTO;

public interface ProductService {
    void addProduct(ProductDTO productDTO);

    void updateProduct(Long productId, ProductDTO productDTO);

    void deleteProduct(Long productId);

    List<ProductDTO> getAvailableProducts();
}
