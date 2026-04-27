package com.jobcard.management.dto.response;

import com.jobcard.management.enums.Role;

public class JwtAuthResponse {

    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private String userId;
    private String name;
    private String email;
    private Role role;

    public JwtAuthResponse() {}

    public JwtAuthResponse(String accessToken, String refreshToken, String tokenType, String userId, String name, String email, Role role) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }

    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }

    public String getTokenType() { return tokenType; }
    public void setTokenType(String tokenType) { this.tokenType = tokenType; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public static JwtAuthResponseBuilder builder() { return new JwtAuthResponseBuilder(); }

    public static class JwtAuthResponseBuilder {
        private String accessToken;
        private String refreshToken;
        private String tokenType;
        private String userId;
        private String name;
        private String email;
        private Role role;

        public JwtAuthResponseBuilder accessToken(String accessToken) { this.accessToken = accessToken; return this; }
        public JwtAuthResponseBuilder refreshToken(String refreshToken) { this.refreshToken = refreshToken; return this; }
        public JwtAuthResponseBuilder tokenType(String tokenType) { this.tokenType = tokenType; return this; }
        public JwtAuthResponseBuilder userId(String userId) { this.userId = userId; return this; }
        public JwtAuthResponseBuilder name(String name) { this.name = name; return this; }
        public JwtAuthResponseBuilder email(String email) { this.email = email; return this; }
        public JwtAuthResponseBuilder role(Role role) { this.role = role; return this; }

        public JwtAuthResponse build() {
            return new JwtAuthResponse(accessToken, refreshToken, tokenType, userId, name, email, role);
        }
    }
}
