package com.taufik.challenge5.Controller;

import com.taufik.challenge5.Model.DTO.MerchantDTO;
import com.taufik.challenge5.Model.DTO.MerchantReportDTO;
import com.taufik.challenge5.Service.MerchantService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping
    public ResponseEntity<List<MerchantDTO>> listMerchants(@RequestParam(required = false) Boolean open) {
        return ResponseEntity.ok(merchantService.list(open));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MerchantDTO> getMerchant(@PathVariable Long id) {
        MerchantDTO merchant = merchantService.show(id);
        return merchant != null ? ResponseEntity.ok(merchant) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MerchantDTO> addMerchant(@RequestBody MerchantDTO merchant) {
        MerchantDTO createdMerchant = merchantService.create(merchant);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMerchant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MerchantDTO> updateMerchant(@PathVariable Long id, @RequestBody MerchantDTO merchant) {
        MerchantDTO existingMerchant = merchantService.show(id);
        if (existingMerchant == null) {
            return ResponseEntity.notFound().build();
        }
        MerchantDTO updatedMerchant = merchantService.update(id, merchant);
        return ResponseEntity.ok(updatedMerchant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMerchant(@PathVariable Long id) {
        return merchantService.delete(id) ? ResponseEntity.ok("Merchant deleted Successfully")
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/report")
    public ResponseEntity<MerchantReportDTO> generateReport(@PathVariable Long id,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        MerchantReportDTO report = merchantService.generateReport(id, startDate, endDate);
        return ResponseEntity.ok(report);
    }
}
