package com.taufik.challenge4.Service;

import com.taufik.challenge4.Model.DTO.ProductDTO;
import com.taufik.challenge4.Model.Entity.Product;
import com.taufik.challenge4.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public void addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        // Set merchant if needed
        // product.setMerchant(merchant);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void updateProduct(Long productId, ProductDTO productDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        // Set merchant if needed
        // product.setMerchant(merchant);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductDTO> getAvailableProducts() {
        List<Product> availableProducts = productRepository.findAll();
        return availableProducts.stream().map(this::mapToProductDTO).collect(Collectors.toList());
    }

    private ProductDTO mapToProductDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductName(product.getProductName());
        dto.setPrice(product.getPrice());
        dto.setMerchantCode(product.getMerchant().getMerchantCode());
        return dto;
    }
}
