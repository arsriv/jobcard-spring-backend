package com.jobcard.management.dto.response;

import com.jobcard.management.model.Officer;

import java.time.LocalDateTime;

public class OfficerResponse {

    private String id;
    private String name;
    private String group;
    private String emailId;
    private String specialization;
    private long assignedJobs;
    private long completedJobs;
    private LocalDateTime createdAt;

    public OfficerResponse() {}

    public static OfficerResponse fromOfficer(Officer officer) {
        OfficerResponse response = new OfficerResponse();
        response.setId(officer.getId());
        response.setName(officer.getName());
        response.setGroup(officer.getGroup());
        response.setEmailId(officer.getEmailId());
        response.setSpecialization(officer.getSpecialization());
        response.setCreatedAt(officer.getCreatedAt());
        return response;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGroup() { return group; }
    public void setGroup(String group) { this.group = group; }

    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public long getAssignedJobs() { return assignedJobs; }
    public void setAssignedJobs(long assignedJobs) { this.assignedJobs = assignedJobs; }

    public long getCompletedJobs() { return completedJobs; }
    public void setCompletedJobs(long completedJobs) { this.completedJobs = completedJobs; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public static OfficerResponseBuilder builder() { return new OfficerResponseBuilder(); }

    public static class OfficerResponseBuilder {
        private String id;
        private String name;
        private String group;
        private String emailId;
        private String specialization;
        private long assignedJobs;
        private long completedJobs;
        private LocalDateTime createdAt;

        public OfficerResponseBuilder id(String id) { this.id = id; return this; }
        public OfficerResponseBuilder name(String name) { this.name = name; return this; }
        public OfficerResponseBuilder group(String group) { this.group = group; return this; }
        public OfficerResponseBuilder emailId(String emailId) { this.emailId = emailId; return this; }
        public OfficerResponseBuilder specialization(String specialization) { this.specialization = specialization; return this; }
        public OfficerResponseBuilder assignedJobs(long assignedJobs) { this.assignedJobs = assignedJobs; return this; }
        public OfficerResponseBuilder completedJobs(long completedJobs) { this.completedJobs = completedJobs; return this; }
        public OfficerResponseBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public OfficerResponse build() {
            OfficerResponse response = new OfficerResponse();
            response.setId(id);
            response.setName(name);
            response.setGroup(group);
            response.setEmailId(emailId);
            response.setSpecialization(specialization);
            response.setAssignedJobs(assignedJobs);
            response.setCompletedJobs(completedJobs);
            response.setCreatedAt(createdAt);
            return response;
        }
    }
}
