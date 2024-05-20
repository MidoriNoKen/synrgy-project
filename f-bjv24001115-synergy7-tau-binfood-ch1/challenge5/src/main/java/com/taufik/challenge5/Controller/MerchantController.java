package com.taufik.challenge5.Controller;

import com.taufik.challenge5.Model.DTO.MerchantDTO;
import com.taufik.challenge5.Model.Entity.Merchant;
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

    @GetMapping("/{code}")
    public ResponseEntity<MerchantDTO> show(@PathVariable Long code) {
        return ResponseEntity.ok(merchantService.show(code));
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody MerchantDTO merchantDTO) {
        merchantService.create(merchantDTO);
        return ResponseEntity.ok("Merchant created successfully");
    }

    @PutMapping("/{code}")
    public ResponseEntity<String> update(@PathVariable Long code, @RequestBody MerchantDTO merchantDTO) {
        merchantService.update(code, merchantDTO);
        return ResponseEntity.ok("Merchant updated successfully");
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<String> delete(@PathVariable Long code) {
        merchantService.delete(code);
        return ResponseEntity.ok("Merchant deleted successfully");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
