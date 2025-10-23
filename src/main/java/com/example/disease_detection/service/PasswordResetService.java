package com.example.disease_detection.service;

import com.example.disease_detection.entity.PasswordResetToken;
import com.example.disease_detection.entity.User;
import com.example.disease_detection.repository.PasswordResetTokenRepository;
import com.example.disease_detection.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class PasswordResetService {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public void generateOtp(String email) {
        // Trim input and check DB
        Optional<User> userOpt = userRepository.findByEmailIgnoreCaseCustom(email.trim());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Email not registered");
        }

        String otp = String.format("%06d", new Random().nextInt(999999));
        PasswordResetToken token = new PasswordResetToken(email.trim(), otp, LocalDateTime.now().plusMinutes(15));
        tokenRepository.save(token);

        emailService.sendOtpEmail(email.trim(), otp);
    }

    public boolean verifyOtp(String email, String otp) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByEmailAndOtp(email.trim(), otp);
        return tokenOpt.isPresent() && tokenOpt.get().getExpiryDate().isAfter(LocalDateTime.now());
    }
}
