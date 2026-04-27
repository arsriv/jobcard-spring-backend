package com.jobcard.management.service;

import com.jobcard.management.dto.request.OfficerCreateRequest;
import com.jobcard.management.dto.response.JwtAuthResponse;
import com.jobcard.management.enums.Role;
import com.jobcard.management.exception.UnauthorizedException;
import com.jobcard.management.model.Officer;
import com.jobcard.management.repository.OfficerRepository;
import com.jobcard.management.security.JwtTokenProvider;
import com.jobcard.management.security.UserPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OfficerAuthService {

    private final OfficerRepository officerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public OfficerAuthService(OfficerRepository officerRepository,
                             PasswordEncoder passwordEncoder,
                             JwtTokenProvider jwtTokenProvider) {
        this.officerRepository = officerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public JwtAuthResponse authenticateOfficer(String email, String password) {
        Officer officer = officerRepository.findByEmailId(email)
                .orElseThrow(() -> new UnauthorizedException("Invalid email or password"));

        if (!passwordEncoder.matches(password, officer.getPassword())) {
            throw new UnauthorizedException("Invalid email or password");
        }

        UserPrincipal principal = UserPrincipal.builder()
                .id(officer.getId())
                .email(officer.getEmailId())
                .name(officer.getName())
                .role(Role.OFFICER)
                .build();

        return generateAuthResponse(principal);
    }

    public JwtAuthResponse registerOfficer(OfficerCreateRequest request) {
        if (officerRepository.existsByEmailId(request.getEmailId())) {
            throw new com.jobcard.management.exception.BadRequestException("Email already registered");
        }

        Officer officer = Officer.builder()
                .id(request.getId())
                .name(request.getName())
                .group(request.getGroup())
                .emailId(request.getEmailId())
                .specialization(request.getSpecialization())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.OFFICER)
                .build();

        officer = officerRepository.save(officer);

        UserPrincipal principal = UserPrincipal.builder()
                .id(officer.getId())
                .email(officer.getEmailId())
                .name(officer.getName())
                .role(Role.OFFICER)
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
