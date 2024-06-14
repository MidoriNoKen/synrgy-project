package com.taufik.challenge6.Controllers;

import com.taufik.challenge6.Models.Dtos.User.UserDto;
import com.taufik.challenge6.Models.Dtos.User.UserRegisterRequestDto;
import com.taufik.challenge6.Models.Dtos.User.UserUpdateRequestDto;
import com.taufik.challenge6.Services.MailService;
import com.taufik.challenge6.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    MailService mailService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterRequestDto userRegisterRequestDto,
            @RequestParam String role) {
        mailService.registerUser(userRegisterRequestDto, role);
        return ResponseEntity.ok("User registered successfully. Check email for OTP.");
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<String> validateOtp(@RequestParam String email, @RequestParam String otp) {
        if (mailService.validateOtp(email, otp)) {
            return ResponseEntity.ok("User activated successfully.");
        }
        return ResponseEntity.badRequest().body("Invalid OTP.");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        mailService.sendPasswordResetOtp(email);
        return ResponseEntity.ok("OTP sent to email.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String otp,
            @RequestParam String newPassword) {
        if (mailService.resetPassword(email, otp, newPassword)) {
            return ResponseEntity.ok("Password reset successfully.");
        }
        return ResponseEntity.badRequest().body("Invalid OTP or OTP expired.");
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        List<UserDto> userList = userService.getList();
        data.put("users", userList);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable("user_id") UUID userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        UserDto userDto = userService.getDtoById(userId);
        data.put("user", userDto);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable("user_id") UUID userId,
            UserUpdateRequestDto userUpdateRequestDto) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        Map<String, Object> data = new HashMap<>();
        UserDto updatedUser = userService.update(userId, userUpdateRequestDto);
        data.put("user", updatedUser);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable("user_id") UUID userId) {
        userService.safeDeleteById(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
