package com.taufik.challenge5.Controller;

import com.taufik.challenge5.Model.DTO.ProductDTO;
import com.taufik.challenge5.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> listProducts(Pageable pageable) {
        return ResponseEntity.ok(productService.list(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> showProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.show(id));
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductDTO productDTO) {
        productService.create(productDTO);
        return ResponseEntity.ok("Product created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        productService.update(id, productDTO);
        return ResponseEntity.ok("Product updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @GetMapping("/merchant/{merchantCode}")
    public ResponseEntity<List<ProductDTO>> getByMerchant(@PathVariable Long merchantCode) {
        return ResponseEntity.ok(productService.getByMerchant(merchantCode));
    }
}
