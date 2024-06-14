package com.taufik.challenge6.Controllers;

import com.taufik.challenge6.Models.Dtos.Merchant.*;
import com.taufik.challenge6.Services.MerchantService;
import com.taufik.challenge6.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("merchant")
public class MerchantController {
    @Autowired
    MerchantService merchantService;
    @Autowired
    UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<Map<String, Object>> addMerchant(
            @RequestBody MerchantCreateRequestDto merchantCreateRequestDto) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        data.put("merchant", merchantService.create(merchantCreateRequestDto));
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllMerchants() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        List<MerchantDto> merchantList = merchantService.getList();
        data.put("merchants", merchantList);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/open_merchants")
    public ResponseEntity<Map<String, Object>> getAllOpenMerchants() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        List<MerchantDto> openMerchantList = merchantService.getOpenList();
        data.put("openMerchants", openMerchantList);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<Map<String, Object>> getMerchantsByUser(@PathVariable("user_id") UUID userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        List<MerchantDto> usersMerchantList = merchantService.getListByUser(userService.getById(userId));
        data.put("usersMerchants", usersMerchantList);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{merchant_id}")
    public ResponseEntity<Map<String, Object>> getMerchantById(@PathVariable("merchant_id") UUID merchantId) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        MerchantDto merchant = merchantService.getDtoById(merchantId);
        data.put("merchant", merchant);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{merchant_id}/report")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<Map<String, Object>> generateMerchantReport(
            @PathVariable("merchant_id") UUID merchantId, @RequestParam(required = false) LocalDateTime startTime,
            @RequestParam(required = false) LocalDateTime endTime) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        MerchantReportDto merchantReport = merchantService.getReport(merchantService.getById(merchantId),
                startTime, endTime);
        data.put("merchantReport", merchantReport);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{merchant_id}")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<Map<String, Object>> updateMerchant(@PathVariable("merchant_id") UUID merchantId,
            MerchantUpdateRequestDto merchantUpdateRequestDto) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        MerchantDto updatedMerchant = merchantService.update(merchantId, merchantUpdateRequestDto);
        data.put("merchant", updatedMerchant);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{merchant_id}")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<Map<String, Object>> deleteMerchant(@PathVariable("merchant_id") UUID merchantId) {
        merchantService.safeDeleteById(merchantId);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
