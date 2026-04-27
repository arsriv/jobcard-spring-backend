package com.jobcard.management.security;

import com.jobcard.management.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private String id;
    private String email;
    private String name;
    private String password;
    private Role role;

    public UserPrincipal() {}

    public UserPrincipal(String id, String email, String name, String password, Role role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return email; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    public static UserPrincipalBuilder builder() { return new UserPrincipalBuilder(); }

    public static class UserPrincipalBuilder {
        private String id;
        private String email;
        private String name;
        private String password;
        private Role role;

        public UserPrincipalBuilder id(String id) { this.id = id; return this; }
        public UserPrincipalBuilder email(String email) { this.email = email; return this; }
        public UserPrincipalBuilder name(String name) { this.name = name; return this; }
        public UserPrincipalBuilder password(String password) { this.password = password; return this; }
        public UserPrincipalBuilder role(Role role) { this.role = role; return this; }

        public UserPrincipal build() {
            return new UserPrincipal(id, email, name, password, role);
        }
    }
}
