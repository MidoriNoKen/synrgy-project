package com.taufik.challenge6.Services.Impl;

import com.taufik.challenge6.Models.Entities.Auth.RoleEnum;
import com.taufik.challenge6.Models.Dtos.User.UserRegisterRequestDto;
import com.taufik.challenge6.Models.Entities.Auth.Role;
import com.taufik.challenge6.Models.Entities.Auth.User;
import com.taufik.challenge6.Repositories.RoleRepository;
import com.taufik.challenge6.Repositories.UserRepository;
import com.taufik.challenge6.Services.MailService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    Environment env;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public void registerUser(UserRegisterRequestDto userRegisterRequestDto, String roleString) {
        User user = modelMapper.map(userRegisterRequestDto, User.class);
        user.setId(UUID.randomUUID());
        user.setActive(false);
        user.setOtp(generateOtp());
        user.setOtpExpirationTime(LocalDateTime.now().plusMinutes(10));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        RoleEnum eRole = RoleEnum.toRoleEnum(roleString);
        Role role = roleRepository.findByName(eRole);
        user.setRole(role);

        userRepository.save(user);
        sendOtpEmail(user.getEmail(), user.getOtp());
    }

    @Override
    public String generateOtp() {
        return UUID.randomUUID().toString().substring(0, 6);
    }

    @Override
    public void sendOtpEmail(String mailAddress, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(env.getProperty("spring.mail.username"));
        message.setTo(mailAddress);
        message.setSubject("OTP Code for Binarfud");
        message.setText("Your OTP code is " + otp);
        javaMailSender.send(message);
    }

    @Override
    public boolean validateOtp(String email, String otp) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getOtp().equals(otp) && user.getOtpExpirationTime().isAfter(LocalDateTime.now())) {
                user.setActive(true);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public void sendPasswordResetOtp(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setOtp(generateOtp());
            user.setOtpExpirationTime(LocalDateTime.now().plusMinutes(10));
            userRepository.save(user);
            sendOtpEmail(email, user.getOtp());
        }
    }

    @Override
    public boolean resetPassword(String email, String otp, String newPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getOtp().equals(otp) && user.getOtpExpirationTime().isAfter(LocalDateTime.now())) {
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }
}