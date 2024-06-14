package com.taufik.challenge6.Controllers;

import com.taufik.challenge6.Models.Dtos.Product.ProductCreateRequestDto;
import com.taufik.challenge6.Models.Dtos.Product.ProductDto;
import com.taufik.challenge6.Models.Dtos.Product.ProductUpdateRequestDto;
import com.taufik.challenge6.Services.ProductService;
import com.taufik.challenge6.Services.MerchantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("menu_item")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    MerchantService merchantService;

    @PostMapping
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<Map<String, Object>> addProduct(
            @RequestBody ProductCreateRequestDto productCreateRequestDto) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        data.put("product", productService.create(productCreateRequestDto));
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/merchant/{merchant_id}")
    public ResponseEntity<Map<String, Object>> getAllProductsByMerchant(
            @PathVariable("merchant_id") UUID merchantId) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        List<ProductDto> productList = productService.getListByMerchant(merchantService.getById(merchantId));
        data.put("products", productList);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/merchant/{merchant_id}/page/{page}")
    public ResponseEntity<Map<String, Object>> getAllProductsByMerchantAndPage(
            @PathVariable("merchant_id") UUID merchantId, @PathVariable("page") int page,
            @RequestParam int productCountPerPage) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        List<ProductDto> productList = productService
                .getListByMerchantAndPage(merchantService.getById(merchantId), page, productCountPerPage);
        data.put("products", productList);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/merchant/{merchant_id}/total_pages")
    public ResponseEntity<Map<String, Object>> getTotalPage(@PathVariable("merchant_id") UUID merchantId,
            @RequestParam int productCountPerPage) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        int totalPage = productService.getTotalPage(merchantService.getById(merchantId), productCountPerPage);
        data.put("totalPages", totalPage);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{menu_item_id}")
    public ResponseEntity<Map<String, Object>> getProductById(@PathVariable("menu_item_id") UUID productId) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        ProductDto product = productService.getDtoById(productId);
        data.put("product", product);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{menu_item_id}/available_sizes")
    public ResponseEntity<Map<String, Object>> getAvailableSizes(@PathVariable("menu_item_id") UUID productId) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        List<String> availableSizes = productService.getAvailableSize(productService.getById(productId));
        data.put("availableSizes", availableSizes);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{menu_item_id}")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<Map<String, Object>> updateProduct(@PathVariable("menu_item_id") UUID productId,
            ProductUpdateRequestDto productUpdateRequestDto) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        ProductDto updatedProduct = productService.update(productId, productUpdateRequestDto);
        data.put("product", updatedProduct);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{menu_item_id}")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable("menu_item_id") UUID productId) {
        productService.safeDeleteById(productId);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
