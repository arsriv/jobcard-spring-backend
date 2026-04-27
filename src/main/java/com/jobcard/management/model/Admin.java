package com.jobcard.management.model;

import com.jobcard.management.enums.Role;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "admins")
public class Admin {

    @Id
    private String id;
    private String name;

    @Indexed(unique = true)
    private String email;

    private String group;
    private String password;
    private Role role = Role.ADMIN;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Admin() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGroup() { return group; }
    public void setGroup(String group) { this.group = group; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public static AdminBuilder builder() { return new AdminBuilder(); }

    public static class AdminBuilder {
        private String id;
        private String name;
        private String email;
        private String group;
        private String password;
        private Role role = Role.ADMIN;

        public AdminBuilder id(String id) { this.id = id; return this; }
        public AdminBuilder name(String name) { this.name = name; return this; }
        public AdminBuilder email(String email) { this.email = email; return this; }
        public AdminBuilder group(String group) { this.group = group; return this; }
        public AdminBuilder password(String password) { this.password = password; return this; }
        public AdminBuilder role(Role role) { this.role = role; return this; }

        public Admin build() {
            Admin admin = new Admin();
            admin.setId(id);
            admin.setName(name);
            admin.setEmail(email);
            admin.setGroup(group);
            admin.setPassword(password);
            admin.setRole(role);
            return admin;
        }
    }
}
