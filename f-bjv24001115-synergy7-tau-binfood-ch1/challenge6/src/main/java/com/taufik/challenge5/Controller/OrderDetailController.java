package com.taufik.challenge5.Controller;

import java.util.List;

import com.taufik.challenge5.Model.DTO.OrderDetailDTO;
import com.taufik.challenge5.Service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders/detail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<List<OrderDetailDTO>> list() {
        return ResponseEntity.ok(orderDetailService.list());
    }

    @PostMapping
    public ResponseEntity<String> createOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        orderDetailService.create(orderDetailDTO);
        return ResponseEntity.ok("Order detail created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrderDetail(@PathVariable Long id, @RequestBody OrderDetailDTO orderDetailDTO) {
        orderDetailService.update(id, orderDetailDTO);
        return ResponseEntity.ok("Order detail updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderDetail(@PathVariable Long id) {
        orderDetailService.delete(id);
        return ResponseEntity.ok("Order detail deleted successfully");
    }
}
