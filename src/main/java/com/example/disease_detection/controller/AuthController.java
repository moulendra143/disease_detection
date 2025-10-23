package com.example.disease_detection.controller;

import com.example.disease_detection.entity.PasswordResetToken;
import com.example.disease_detection.entity.User;
import com.example.disease_detection.repository.PasswordResetTokenRepository;
import com.example.disease_detection.repository.UserRepository;
import com.example.disease_detection.service.PasswordResetService;
import com.example.disease_detection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    // ---------------- SIGNUP ----------------
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User savedUser = userService.registerUser(user);
            Map<String, Object> response = Map.of(
                    "id", savedUser.getId(),
                    "fullName", savedUser.getFullName(),
                    "email", savedUser.getEmail()
            );
            return ResponseEntity.ok(Map.of("message", "Signup successful", "user", response));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("message", "Server error"));
        }
    }

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        Optional<User> validUser = userService.loginUser(user.getEmail(), user.getPassword());
        if (validUser.isPresent()) {
            User u = validUser.get();
            Map<String, Object> userData = Map.of(
                    "id", u.getId(),
                    "fullName", u.getFullName(),
                    "email", u.getEmail()
            );
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Login successful",
                    "user", userData
            ));
        } else {
            return ResponseEntity.status(401).body(Map.of(
                    "success", false,
                    "message", "Invalid email or password"
            ));
        }
    }

    // ---------------- FORGOT PASSWORD (Generate OTP) ----------------
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        if (email == null || email.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email is required"));
        }

        try {
            passwordResetService.generateOtp(email);
            return ResponseEntity.ok(Map.of("message", "OTP sent successfully to your email."));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

    // ---------------- VERIFY OTP ----------------
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String otp = body.get("otp");

        if (email == null || email.isBlank() || otp == null || otp.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email and OTP are required"));
        }

        boolean valid = passwordResetService.verifyOtp(email, otp);
        if (valid) {
            return ResponseEntity.ok(Map.of("message", "OTP verified successfully."));
        } else {
            return ResponseEntity.status(400).body(Map.of("message", "Invalid or expired OTP."));
        }
    }

    // ---------------- RESET PASSWORD ----------------
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String otp = body.get("otp");
        String newPassword = body.get("newPassword");

        if (email == null || email.isBlank() ||
            otp == null || otp.isBlank() ||
            newPassword == null || newPassword.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email, OTP, and newPassword are required"));
        }

        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByEmailAndOtp(email.trim(), otp);
        if (tokenOpt.isEmpty()) {
            return ResponseEntity.status(400).body(Map.of("message", "Invalid OTP or email"));
        }

        PasswordResetToken token = tokenOpt.get();
        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(400).body(Map.of("message", "OTP expired"));
        }

        if (!isStrongPassword(newPassword)) {
            return ResponseEntity.badRequest().body(Map.of("message",
                    "Password must be at least 8 characters and include upper, lower, number, and special char"));
        }

        Optional<User> userOpt = userRepository.findByEmail(email.trim());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("message", "User not found"));
        }

        User user = userOpt.get();
        user.setPassword(userService.encodePassword(newPassword));
        userRepository.save(user);

        // Delete used OTP token
        tokenRepository.delete(token);

        return ResponseEntity.ok(Map.of("message", "Password reset successful"));
    }

    // ---------------- PASSWORD VALIDATION ----------------
    private boolean isStrongPassword(String pwd) {
        if (pwd.length() < 8) return false;
        boolean upper = pwd.chars().anyMatch(Character::isUpperCase);
        boolean lower = pwd.chars().anyMatch(Character::isLowerCase);
        boolean digit = pwd.chars().anyMatch(Character::isDigit);
        boolean special = pwd.chars().anyMatch(ch -> "!@#$%^&*()-_=+[]{};:'\",.<>/?\\|`~".indexOf(ch) >= 0);
        return upper && lower && digit && special;
    }
}
