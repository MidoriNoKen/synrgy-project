package com.taufik.challenge6.Services;

import org.springframework.stereotype.Service;

import com.taufik.challenge6.Models.Dtos.User.UserRegisterRequestDto;

@Service
public interface MailService {
    void registerUser(UserRegisterRequestDto userRegisterRequestDto, String role);

    String generateOtp();

    void sendOtpEmail(String mailAddress, String otp);

    boolean validateOtp(String email, String otp);

    void sendPasswordResetOtp(String email);

    boolean resetPassword(String email, String otp, String newPassword);
}
