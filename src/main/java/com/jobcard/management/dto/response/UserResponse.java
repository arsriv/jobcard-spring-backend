package com.jobcard.management.dto.response;

import com.jobcard.management.model.User;

import java.time.LocalDateTime;

public class UserResponse {

    private String id;
    private String name;
    private String email;
    private String group;
    private long totalJobs;
    private long activeJobs;
    private LocalDateTime createdAt;

    public UserResponse() {}

    public static UserResponse fromUser(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setGroup(user.getGroup());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGroup() { return group; }
    public void setGroup(String group) { this.group = group; }

    public long getTotalJobs() { return totalJobs; }
    public void setTotalJobs(long totalJobs) { this.totalJobs = totalJobs; }

    public long getActiveJobs() { return activeJobs; }
    public void setActiveJobs(long activeJobs) { this.activeJobs = activeJobs; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public static UserResponseBuilder builder() { return new UserResponseBuilder(); }

    public static class UserResponseBuilder {
        private String id;
        private String name;
        private String email;
        private String group;
        private long totalJobs;
        private long activeJobs;
        private LocalDateTime createdAt;

        public UserResponseBuilder id(String id) { this.id = id; return this; }
        public UserResponseBuilder name(String name) { this.name = name; return this; }
        public UserResponseBuilder email(String email) { this.email = email; return this; }
        public UserResponseBuilder group(String group) { this.group = group; return this; }
        public UserResponseBuilder totalJobs(long totalJobs) { this.totalJobs = totalJobs; return this; }
        public UserResponseBuilder activeJobs(long activeJobs) { this.activeJobs = activeJobs; return this; }
        public UserResponseBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public UserResponse build() {
            UserResponse response = new UserResponse();
            response.setId(id);
            response.setName(name);
            response.setEmail(email);
            response.setGroup(group);
            response.setTotalJobs(totalJobs);
            response.setActiveJobs(activeJobs);
            response.setCreatedAt(createdAt);
            return response;
        }
    }
}
