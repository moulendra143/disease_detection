package com.example.disease_detection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String otp) {
        String subject = "Your Password Reset OTP";
        String message = "Hello,\n\nYour OTP for password reset is: " + otp +
                "\n\nThis OTP will expire in 15 minutes.\n\nThank you,\nDisease Detection Team";

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
        System.out.println("✅ OTP email sent to: " + toEmail);
    }
}
