package com.career.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    @Autowired
    private JavaMailSender mailSender;

    // Helper class to store OTP and its expiry time
    private static class OtpDetails {
        final String otp;
        final long expiryTime;

        OtpDetails(String otp, long expiryTime) {
            this.otp = otp;
            this.expiryTime = expiryTime;
        }
    }

    // Temporary memory to store OTPs. Key = email, Value = OtpDetails
    private final Map<String, OtpDetails> otpStorage = new ConcurrentHashMap<>();

    public void generateAndSendOtp(String email) {
        if (email != null) email = email.trim();
        
        // 1. Generate 6 digit OTP
        String otp = String.format("%06d", new Random().nextInt(999999));

        // 2. Save it in memory with 1 minute expiry
        long expiryTime = System.currentTimeMillis() + (60 * 1000); // 1 minute from now
        otpStorage.put(email, new OtpDetails(otp, expiryTime));

        // 3. Send Email
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Your Career Assessment Registration OTP");
            message.setText("Your OTP for registration is: " + otp + "\n\nThis code is valid for 1 minute.");
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println("Failed to send email to " + email);
        }
        
        // Print to console for easy local testing
        System.out.println("TESTING ONLY - OTP generated for [" + email + "] is: " + otp);
    }

    public boolean validateOtp(String email, String otp) {
        if (email != null) email = email.trim();
        if (otp != null) otp = otp.trim();

        System.out.println("Attempting to validate OTP for [" + email + "] with provided OTP: [" + otp + "]");

        OtpDetails details = otpStorage.get(email);
        
        if (details == null) {
            System.out.println("Validation failed: No OTP found in memory for this email. (Did the server restart?)");
            return false;
        }

        if (!details.otp.equals(otp)) {
            System.out.println("Validation failed: Incorrect OTP. Expected [" + details.otp + "] but got [" + otp + "]");
            return false;
        }

        if (System.currentTimeMillis() > details.expiryTime) {
            System.out.println("Validation failed: OTP expired. Time limit of 1 min exceeded.");
            otpStorage.remove(email); // Clear expired OTP
            return false;
        }

        System.out.println("Validation successful for [" + email + "]");
        otpStorage.remove(email); // Clear OTP after successful use
        return true;
    }
}
