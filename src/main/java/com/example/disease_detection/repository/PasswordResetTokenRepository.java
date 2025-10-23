package com.example.disease_detection.repository;

import com.example.disease_detection.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByEmailAndOtp(String email, String otp);

    Optional<PasswordResetToken> findByEmail(String email);
}
