package com.taufik.challenge5.Controller;

import com.taufik.challenge5.Model.DTO.MerchantDTO;
import com.taufik.challenge5.Service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping
    public ResponseEntity<List<MerchantDTO>> list(@RequestParam(required = false) Boolean open) {
        return ResponseEntity.ok(merchantService.list(open));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MerchantDTO> show(@PathVariable Long id) {
        return ResponseEntity.ok(merchantService.show(id));
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody MerchantDTO merchantDTO) {
        merchantService.create(merchantDTO);
        return ResponseEntity.ok("Merchant created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody MerchantDTO merchantDTO) {
        merchantService.update(id, merchantDTO);
        return ResponseEntity.ok("Merchant updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        merchantService.delete(id);
        return ResponseEntity.ok("Merchant deleted successfully");
    }
}
