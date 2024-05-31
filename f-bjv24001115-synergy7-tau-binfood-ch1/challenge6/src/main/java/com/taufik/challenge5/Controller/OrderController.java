package com.taufik.challenge5.Controller;

import com.taufik.challenge5.Model.DTO.OrderDTO;
import com.taufik.challenge5.Model.DTO.OrderDetailDTO;
import com.taufik.challenge5.Model.DTO.OrderInvoiceDTO;
import com.taufik.challenge5.Model.DTO.UserDTO;
import com.taufik.challenge5.Service.InvoiceService;
import com.taufik.challenge5.Service.OrderDetailService;
import com.taufik.challenge5.Service.OrderService;
import com.taufik.challenge5.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> list() {
        return ResponseEntity.ok(orderService.list());
    }

    @GetMapping("/{userId}/invoice")
    public ResponseEntity<byte[]> generateInvoice(@PathVariable Long userId) {
        try {
            UserDTO user = userService.show(userId);
            List<OrderInvoiceDTO> orders = orderService.listOrdersByUserId(userId);

            if (user == null || orders == null || orders.isEmpty()) {
                return ResponseEntity.status(404).build();
            }

            byte[] pdf = invoiceService.generateInvoice(user, orders);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("invoice", "invoice.pdf");
            return ResponseEntity.ok().headers(headers).body(pdf);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> show(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.show(id));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<OrderDetailDTO> showDetail(@PathVariable Long id) {
        return ResponseEntity.ok(orderDetailService.show(id));
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
