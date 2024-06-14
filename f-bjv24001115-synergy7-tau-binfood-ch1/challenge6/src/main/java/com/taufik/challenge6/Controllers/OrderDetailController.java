package com.taufik.challenge6.Controllers;

import com.taufik.challenge6.Models.Dtos.OrderDetail.OrderDetailCreateRequestDto;
import com.taufik.challenge6.Models.Dtos.OrderDetail.OrderDetailDto;
import com.taufik.challenge6.Models.Dtos.OrderDetail.OrderDetailUpdateRequestDto;
import com.taufik.challenge6.Services.ProductService;
import com.taufik.challenge6.Services.OrderDetailService;
import com.taufik.challenge6.Services.OrderService;

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
@RequestMapping("order_detail")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Map<String, Object>> addOrderDetail(
            @RequestBody OrderDetailCreateRequestDto orderDetailCreateRequestDto) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        data.put("orderDetail", orderDetailService.create(orderDetailCreateRequestDto));
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/order/{order_id}")
    public ResponseEntity<Map<String, Object>> getOrderDetailsByOrder(@PathVariable("order_id") UUID orderId) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        List<OrderDetailDto> orderDetailList = orderDetailService.getListByOrder(orderService.getById(orderId));
        data.put("orderDetails", orderDetailList);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/product/{menu_item_id}")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<Map<String, Object>> getOrderDetailsByProduct(
            @PathVariable("menu_item_id") UUID productId) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        List<OrderDetailDto> orderDetailList = orderDetailService
                .getListByProduct(productService.getById(productId));
        data.put("orderDetails", orderDetailList);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{order_detail_id}")
    public ResponseEntity<Map<String, Object>> getOrderDetailById(@PathVariable("order_detail_id") UUID orderDetailId) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        OrderDetailDto orderDetail = orderDetailService.getDtoById(orderDetailId);
        data.put("orderDetail", orderDetail);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{order_detail_id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Map<String, Object>> updateOrderDetail(@PathVariable("order_detail_id") UUID orderDetailId,
            OrderDetailUpdateRequestDto orderDetailUpdateRequestDto) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        OrderDetailDto updatedOrderDetail = orderDetailService.update(orderDetailId, orderDetailUpdateRequestDto);
        data.put("orderDetail", updatedOrderDetail);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{order_detail_id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Map<String, Object>> deleteOrderDetail(@PathVariable("order_detail_id") UUID orderDetailId) {
        orderDetailService.safeDeleteById(orderDetailId);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
