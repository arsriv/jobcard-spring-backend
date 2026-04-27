package com.jobcard.management.service;

import com.jobcard.management.dto.request.LoginRequest;
import com.jobcard.management.dto.request.UserSignupRequest;
import com.jobcard.management.dto.response.JwtAuthResponse;
import com.jobcard.management.enums.Role;
import com.jobcard.management.exception.UnauthorizedException;
import com.jobcard.management.model.User;
import com.jobcard.management.repository.UserRepository;
import com.jobcard.management.security.JwtTokenProvider;
import com.jobcard.management.security.UserPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(UserRepository userRepository,
                      PasswordEncoder passwordEncoder,
                      JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public JwtAuthResponse authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException("Invalid email or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UnauthorizedException("Invalid email or password");
        }

        UserPrincipal principal = UserPrincipal.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(Role.USER)
                .build();

        return generateAuthResponse(principal);
    }

    public JwtAuthResponse registerUser(UserSignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new com.jobcard.management.exception.BadRequestException("Email already registered");
        }

        User user = User.builder()
                .id(request.getEmpId())
                .name(request.getName())
                .email(request.getEmail())
                .group(request.getGroup())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        user = userRepository.save(user);

        UserPrincipal principal = UserPrincipal.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(Role.USER)
                .build();

        return generateAuthResponse(principal);
    }

    public JwtAuthResponse refreshAccessToken(String refreshToken) {
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new UnauthorizedException("Invalid refresh token");
        }

        UserPrincipal principal = jwtTokenProvider.getUserPrincipalFromToken(refreshToken);
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
