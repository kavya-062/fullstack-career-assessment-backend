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
        long expiryTime = System.currentTimeMillis() + (60 * 1000); // 1 minute
        otpStorage.put(email, new OtpDetails(otp, expiryTime));

        // 3. Send Email
        try {
            System.out.println("🚀 Trying to send OTP to: " + email);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Your Career Assessment Registration OTP");
            message.setText(
                    "Your OTP for registration is: " + otp +
                    "\n\nThis code is valid for 1 minute."
            );

            mailSender.send(message);

            System.out.println("✅ OTP email sent successfully to: " + email);

        } catch (Exception e) {
            System.out.println("❌ Failed to send email to " + email);
            e.printStackTrace();   // 🔥 VERY IMPORTANT (shows real error)
        }

        // Debug (remove later if needed)
        System.out.println("TESTING ONLY - OTP generated for [" + email + "] is: " + otp);
    }

    public boolean validateOtp(String email, String otp) {
        if (email != null) email = email.trim();
        if (otp != null) otp = otp.trim();

        System.out.println("🔍 Validating OTP for [" + email + "] with input [" + otp + "]");

        OtpDetails details = otpStorage.get(email);

        if (details == null) {
            System.out.println("❌ No OTP found (maybe expired or server restarted)");
            return false;
        }

        if (!details.otp.equals(otp)) {
            System.out.println("❌ Incorrect OTP. Expected [" + details.otp + "] but got [" + otp + "]");
            return false;
        }

        if (System.currentTimeMillis() > details.expiryTime) {
            System.out.println("❌ OTP expired (1 minute passed)");
            otpStorage.remove(email);
            return false;
        }

        System.out.println("✅ OTP validation successful for [" + email + "]");
        otpStorage.remove(email);
        return true;
    }
}