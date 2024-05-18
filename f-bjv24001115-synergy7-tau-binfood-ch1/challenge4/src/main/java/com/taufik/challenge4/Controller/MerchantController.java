package com.taufik.challenge4.Controller;

import com.taufik.challenge4.Model.DTO.MerchantDTO;
import com.taufik.challenge4.Service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping
    public ResponseEntity<String> addMerchant(@RequestBody MerchantDTO merchantDTO) {
        merchantService.addMerchant(merchantDTO);
        return ResponseEntity.ok("Merchant added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMerchant(@PathVariable Long id, @RequestBody MerchantDTO merchantDTO) {
        merchantService.updateMerchant(id, merchantDTO);
        return ResponseEntity.ok("Merchant updated successfully");
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> changeMerchantStatus(@PathVariable Long id, @RequestParam boolean status) {
        merchantService.changeMerchantStatus(id, status);
        return ResponseEntity.ok("Merchant status updated successfully");
    }

    @GetMapping("/open")
    public ResponseEntity<List<MerchantDTO>> getOpenMerchants() {
        List<MerchantDTO> openMerchants = merchantService.getOpenMerchants();
        return ResponseEntity.ok(openMerchants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MerchantDTO> getMerchantById(@PathVariable Long id) {
        MerchantDTO merchantDTO = merchantService.getMerchantById(id);
        return ResponseEntity.ok(merchantDTO);
    }
}
