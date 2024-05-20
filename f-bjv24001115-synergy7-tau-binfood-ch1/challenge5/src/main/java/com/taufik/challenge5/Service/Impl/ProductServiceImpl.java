package com.taufik.challenge5.Service.Impl;

import com.taufik.challenge5.Model.DTO.ProductDTO;
import com.taufik.challenge5.Model.Entity.Product;
import com.taufik.challenge5.Repository.ProductRepository;
import com.taufik.challenge5.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<ProductDTO> list(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(this::convertToDTO);
    }

    @Override
    public ProductDTO show(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToDTO(product);
    }

    @Override
    public List<ProductDTO> getByMerchant(Long merchantCode) {
        List<Product> products;
        if (merchantCode != null) {
            products = productRepository.findByMerchantCode(merchantCode);
        } else {
            products = productRepository.findAll();
        }
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void create(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setMerchant(productDTO.getMerchant());
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void update(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setMerchant(productDTO.getMerchant());
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setCode(product.getCode());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setMerchant(product.getMerchant());
        return dto;
    }
}
