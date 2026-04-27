package com.jobcard.management.service;

import com.jobcard.management.dto.request.AdminSignupRequest;
import com.jobcard.management.dto.response.JwtAuthResponse;
import com.jobcard.management.enums.Role;
import com.jobcard.management.exception.UnauthorizedException;
import com.jobcard.management.model.Admin;
import com.jobcard.management.repository.AdminRepository;
import com.jobcard.management.security.JwtTokenProvider;
import com.jobcard.management.security.UserPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AdminAuthService(AdminRepository adminRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public JwtAuthResponse authenticateAdmin(String email, String password) {
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException("Invalid email or password"));

        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new UnauthorizedException("Invalid email or password");
        }

        UserPrincipal principal = UserPrincipal.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .name(admin.getName())
                .role(Role.ADMIN)
                .build();

        return generateAuthResponse(principal);
    }

    public JwtAuthResponse registerAdmin(AdminSignupRequest request) {
        if (adminRepository.existsByEmail(request.getEmail())) {
            throw new com.jobcard.management.exception.BadRequestException("Email already registered");
        }

        Admin admin = Admin.builder()
                .id(request.getEmpId())
                .name(request.getName())
                .email(request.getEmail())
                .group(request.getGroup())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();

        admin = adminRepository.save(admin);

        UserPrincipal principal = UserPrincipal.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .name(admin.getName())
                .role(Role.ADMIN)
                .build();

        return generateAuthResponse(principal);
    }

    private JwtAuthResponse generateAuthResponse(UserPrincipal principal) {
        String accessToken = jwtTokenProvider.generateAccessToken(principal);
        String refreshToken = jwtTokenProvider.generateRefreshToken(principal);

        return JwtAuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .userId(principal.getId())
                .name(principal.getName())
                .email(principal.getEmail())
                .role(principal.getRole())
                .build();
    }
}
