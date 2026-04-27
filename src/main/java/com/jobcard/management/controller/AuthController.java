package com.jobcard.management.controller;

import com.jobcard.management.dto.request.*;
import com.jobcard.management.dto.response.ApiResponse;
import com.jobcard.management.dto.response.JwtAuthResponse;
import com.jobcard.management.enums.Role;
import com.jobcard.management.exception.BadRequestException;
import com.jobcard.management.service.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final OfficerAuthService officerAuthService;
    private final AdminAuthService adminAuthService;
    private final PasswordResetService passwordResetService;
    private final OtpService otpService;

    public AuthController(AuthService authService,
                        OfficerAuthService officerAuthService,
                        AdminAuthService adminAuthService,
                        PasswordResetService passwordResetService,
                        OtpService otpService) {
        this.authService = authService;
        this.officerAuthService = officerAuthService;
        this.adminAuthService = adminAuthService;
        this.passwordResetService = passwordResetService;
        this.otpService = otpService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtAuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        JwtAuthResponse response = authService.authenticateUser(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(ApiResponse.success("Login successful", response));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<JwtAuthResponse>> signup(@Valid @RequestBody UserSignupRequest request) {
        JwtAuthResponse response = authService.registerUser(request);
        return ResponseEntity.ok(ApiResponse.success("Registration successful", response));
    }

    @PostMapping("/admin/login")
    public ResponseEntity<ApiResponse<JwtAuthResponse>> adminLogin(@Valid @RequestBody LoginRequest request) {
        JwtAuthResponse response = adminAuthService.authenticateAdmin(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(ApiResponse.success("Admin login successful", response));
    }

    @PostMapping("/admin/signup")
    public ResponseEntity<ApiResponse<JwtAuthResponse>> adminSignup(@Valid @RequestBody AdminSignupRequest request) {
        JwtAuthResponse response = adminAuthService.registerAdmin(request);
        return ResponseEntity.ok(ApiResponse.success("Admin registration successful", response));
    }

    @PostMapping("/officer/login")
    public ResponseEntity<ApiResponse<JwtAuthResponse>> officerLogin(@Valid @RequestBody LoginRequest request) {
        JwtAuthResponse response = officerAuthService.authenticateOfficer(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(ApiResponse.success("Officer login successful", response));
    }

    @PostMapping("/officer/signup")
    public ResponseEntity<ApiResponse<JwtAuthResponse>> officerSignup(@Valid @RequestBody OfficerCreateRequest request) {
        JwtAuthResponse response = officerAuthService.registerOfficer(request);
        return ResponseEntity.ok(ApiResponse.success("Officer registration successful", response));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<JwtAuthResponse>> refreshToken(@RequestBody RefreshTokenRequest request) {
        JwtAuthResponse response = authService.refreshAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(ApiResponse.success("Token refreshed", response));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<Void>> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequest request,
            @RequestParam(defaultValue = "USER") Role role) {
        passwordResetService.sendOtp(request, role);
        return ResponseEntity.ok(ApiResponse.success("OTP sent to email"));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse<Void>> verifyOtp(
            @Valid @RequestBody OtpVerificationRequest request,
            @RequestParam(defaultValue = "USER") Role role) {
        if (!otpService.verifyOtp(request.getEmail(), request.getOtp(), role)) {
            throw new BadRequestException("Invalid or expired OTP");
        }
        return ResponseEntity.ok(ApiResponse.success("OTP verified"));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<Void>> resetPassword(
            @Valid @RequestBody ResetPasswordRequest request,
            @RequestParam(defaultValue = "USER") Role role) {
        passwordResetService.resetPassword(request, role);
        return ResponseEntity.ok(ApiResponse.success("Password reset successful"));
    }
}
