package com.jobcard.management.service;

import com.jobcard.management.enums.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${app.otp.expiration-seconds}")
    private int otpExpirationSeconds;

    private final Map<String, OtpData> otpStore = new ConcurrentHashMap<>();

    public OtpService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private record OtpData(String otp, Role role, long expirationTime) {}

    public void generateAndSendOtp(String email, Role role) {
        String otp = String.format("%06d", new Random().nextInt(999999));
        long expirationTime = System.currentTimeMillis() + (otpExpirationSeconds * 1000L);

        otpStore.put(email, new OtpData(otp, role, expirationTime));

        String subject = "OTP for Password Reset - Job Card Management";
        String body = String.format(
                "Dear User,\n\nYour OTP for password reset is: %s\n\nPlease use this to proceed with resetting your password.\n\nThis OTP will expire in %d minutes.\n\n- Admin Team",
                otp, otpExpirationSeconds / 60
        );

        sendEmail(email, subject, body);
    }

    public boolean verifyOtp(String email, String otp, Role role) {
        OtpData data = otpStore.get(email);

        if (data == null) {
            return false;
        }

        if (System.currentTimeMillis() > data.expirationTime()) {
            otpStore.remove(email);
            return false;
        }

        if (!data.otp().equals(otp) || data.role() != role) {
            return false;
        }

        otpStore.remove(email);
        return true;
    }

    private void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
        } catch (Exception e) {
            // Log error but don't fail
        }
    }
}
