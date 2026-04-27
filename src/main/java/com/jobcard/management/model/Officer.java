package com.jobcard.management.model;

import com.jobcard.management.enums.Role;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "officers")
public class Officer {

    @Id
    private String id;
    private String name;
    private String group;

    @Indexed(unique = true)
    private String emailId;

    private String password;
    private String specialization;
    private Role role = Role.OFFICER;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Officer() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGroup() { return group; }
    public void setGroup(String group) { this.group = group; }

    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public static OfficerBuilder builder() { return new OfficerBuilder(); }

    public static class OfficerBuilder {
        private String id;
        private String name;
        private String group;
        private String emailId;
        private String password;
        private String specialization;
        private Role role = Role.OFFICER;

        public OfficerBuilder id(String id) { this.id = id; return this; }
        public OfficerBuilder name(String name) { this.name = name; return this; }
        public OfficerBuilder group(String group) { this.group = group; return this; }
        public OfficerBuilder emailId(String emailId) { this.emailId = emailId; return this; }
        public OfficerBuilder password(String password) { this.password = password; return this; }
        public OfficerBuilder specialization(String specialization) { this.specialization = specialization; return this; }
        public OfficerBuilder role(Role role) { this.role = role; return this; }

        public Officer build() {
            Officer officer = new Officer();
            officer.setId(id);
            officer.setName(name);
            officer.setGroup(group);
            officer.setEmailId(emailId);
            officer.setPassword(password);
            officer.setSpecialization(specialization);
            officer.setRole(role);
            return officer;
        }
    }
}
