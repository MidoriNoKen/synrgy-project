package com.taufik.challenge5.Service;

import com.taufik.challenge5.Model.DTO.ProductDTO;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductDTO> list(Pageable pageable);

    ProductDTO show(Long id);

    List<ProductDTO> getByMerchant(Long merchantCode);

    void create(ProductDTO productDTO);

    void update(Long id, ProductDTO productDTO);

    void delete(Long id);
}
