package com.taufik.challenge5.Controller;

import com.taufik.challenge5.Model.DTO.OrderDTO;
import com.taufik.challenge5.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> list() {
        return ResponseEntity.ok(orderService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> show(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.show(id));
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody OrderDTO orderDTO) {
        orderService.create(orderDTO);
        return ResponseEntity.ok("Order created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        orderService.update(id, orderDTO);
        return ResponseEntity.ok("Order updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.ok("Order deleted successfully");
    }
}
