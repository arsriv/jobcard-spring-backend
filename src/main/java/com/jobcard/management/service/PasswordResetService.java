package com.jobcard.management.service;

import com.jobcard.management.dto.request.ForgotPasswordRequest;
import com.jobcard.management.dto.request.ResetPasswordRequest;
import com.jobcard.management.enums.Role;
import com.jobcard.management.exception.UnauthorizedException;
import com.jobcard.management.model.Admin;
import com.jobcard.management.model.Officer;
import com.jobcard.management.model.User;
import com.jobcard.management.repository.AdminRepository;
import com.jobcard.management.repository.OfficerRepository;
import com.jobcard.management.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {

    private final UserRepository userRepository;
    private final OfficerRepository officerRepository;
    private final AdminRepository adminRepository;
    private final OtpService otpService;
    private final PasswordEncoder passwordEncoder;

    public PasswordResetService(UserRepository userRepository,
                               OfficerRepository officerRepository,
                               AdminRepository adminRepository,
                               OtpService otpService,
                               PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.officerRepository = officerRepository;
        this.adminRepository = adminRepository;
        this.otpService = otpService;
        this.passwordEncoder = passwordEncoder;
    }

    public void sendOtp(ForgotPasswordRequest request, Role role) {
        String email = request.getEmail();
        validateEmailExists(email, role);
        otpService.generateAndSendOtp(email, role);
    }

    public void resetPassword(ResetPasswordRequest request, Role role) {
        String email = request.getEmail();
        String newPassword = request.getNewPassword();
        String confirmPassword = request.getConfirmPassword();

        if (!newPassword.equals(confirmPassword)) {
            throw new com.jobcard.management.exception.BadRequestException("Passwords do not match");
        }

        switch (role) {
            case USER -> {
                User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new UnauthorizedException("User not found"));
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
            }
            case OFFICER -> {
                Officer officer = officerRepository.findByEmailId(email)
                        .orElseThrow(() -> new UnauthorizedException("Officer not found"));
                officer.setPassword(passwordEncoder.encode(newPassword));
                officerRepository.save(officer);
            }
            case ADMIN -> {
                Admin admin = adminRepository.findByEmail(email)
                        .orElseThrow(() -> new UnauthorizedException("Admin not found"));
                admin.setPassword(passwordEncoder.encode(newPassword));
                adminRepository.save(admin);
            }
        }
    }

    private void validateEmailExists(String email, Role role) {
        boolean exists = switch (role) {
            case USER -> userRepository.existsByEmail(email);
            case OFFICER -> officerRepository.existsByEmailId(email);
            case ADMIN -> adminRepository.existsByEmail(email);
        };

        if (!exists) {
            throw new com.jobcard.management.exception.BadRequestException("Email not found");
        }
    }
}
