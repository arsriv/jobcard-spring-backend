package com.jobcard.management.model;

import com.jobcard.management.enums.JobStatus;
import com.jobcard.management.enums.Priority;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "service_requests")
public class ServiceRequest {

    @Id
    private String id;
    private String userName;
    private String empId;
    private String division;
    private String email;
    private String location;
    private String projectNo;
    private String machineType;
    private String modelNo;
    private String serialNo;
    private String partNo;
    private String pirNo;
    private String operatingSystem;
    private List<String> services;
    private JobStatus status = JobStatus.PENDING;
    private String assignedTo = "Not Assigned";
    private Priority priority = Priority.MEDIUM;
    private String otherMachine;
    private String otherOs;
    private String documentType;
    private String networkType;
    private String additionalServices;
    private String notes;

    @CreatedDate
    private LocalDateTime submissionDate;

    @LastModifiedDate
    private LocalDateTime lastUpdated;

    public ServiceRequest() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }

    public String getDivision() { return division; }
    public void setDivision(String division) { this.division = division; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getProjectNo() { return projectNo; }
    public void setProjectNo(String projectNo) { this.projectNo = projectNo; }

    public String getMachineType() { return machineType; }
    public void setMachineType(String machineType) { this.machineType = machineType; }

    public String getModelNo() { return modelNo; }
    public void setModelNo(String modelNo) { this.modelNo = modelNo; }

    public String getSerialNo() { return serialNo; }
    public void setSerialNo(String serialNo) { this.serialNo = serialNo; }

    public String getPartNo() { return partNo; }
    public void setPartNo(String partNo) { this.partNo = partNo; }

    public String getPirNo() { return pirNo; }
    public void setPirNo(String pirNo) { this.pirNo = pirNo; }

    public String getOperatingSystem() { return operatingSystem; }
    public void setOperatingSystem(String operatingSystem) { this.operatingSystem = operatingSystem; }

    public List<String> getServices() { return services; }
    public void setServices(List<String> services) { this.services = services; }

    public JobStatus getStatus() { return status; }
    public void setStatus(JobStatus status) { this.status = status; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }

    public String getOtherMachine() { return otherMachine; }
    public void setOtherMachine(String otherMachine) { this.otherMachine = otherMachine; }

    public String getOtherOs() { return otherOs; }
    public void setOtherOs(String otherOs) { this.otherOs = otherOs; }

    public String getDocumentType() { return documentType; }
    public void setDocumentType(String documentType) { this.documentType = documentType; }

    public String getNetworkType() { return networkType; }
    public void setNetworkType(String networkType) { this.networkType = networkType; }

    public String getAdditionalServices() { return additionalServices; }
    public void setAdditionalServices(String additionalServices) { this.additionalServices = additionalServices; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDateTime getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(LocalDateTime submissionDate) { this.submissionDate = submissionDate; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }

    public static ServiceRequestBuilder builder() { return new ServiceRequestBuilder(); }

    public static class ServiceRequestBuilder {
        private String id;
        private String userName;
        private String empId;
        private String division;
        private String email;
        private String location;
        private String projectNo;
        private String machineType;
        private String modelNo;
        private String serialNo;
        private String partNo;
        private String pirNo;
        private String operatingSystem;
        private List<String> services;
        private JobStatus status = JobStatus.PENDING;
        private String assignedTo = "Not Assigned";
        private Priority priority = Priority.MEDIUM;
        private String otherMachine;
        private String otherOs;
        private String documentType;
        private String networkType;
        private String additionalServices;
        private String notes;
        private LocalDateTime submissionDate;
        private LocalDateTime lastUpdated;

        public ServiceRequestBuilder id(String id) { this.id = id; return this; }
        public ServiceRequestBuilder userName(String userName) { this.userName = userName; return this; }
        public ServiceRequestBuilder empId(String empId) { this.empId = empId; return this; }
        public ServiceRequestBuilder division(String division) { this.division = division; return this; }
        public ServiceRequestBuilder email(String email) { this.email = email; return this; }
        public ServiceRequestBuilder location(String location) { this.location = location; return this; }
        public ServiceRequestBuilder projectNo(String projectNo) { this.projectNo = projectNo; return this; }
        public ServiceRequestBuilder machineType(String machineType) { this.machineType = machineType; return this; }
        public ServiceRequestBuilder modelNo(String modelNo) { this.modelNo = modelNo; return this; }
        public ServiceRequestBuilder serialNo(String serialNo) { this.serialNo = serialNo; return this; }
        public ServiceRequestBuilder partNo(String partNo) { this.partNo = partNo; return this; }
        public ServiceRequestBuilder pirNo(String pirNo) { this.pirNo = pirNo; return this; }
        public ServiceRequestBuilder operatingSystem(String operatingSystem) { this.operatingSystem = operatingSystem; return this; }
        public ServiceRequestBuilder services(List<String> services) { this.services = services; return this; }
        public ServiceRequestBuilder status(JobStatus status) { this.status = status; return this; }
        public ServiceRequestBuilder assignedTo(String assignedTo) { this.assignedTo = assignedTo; return this; }
        public ServiceRequestBuilder priority(Priority priority) { this.priority = priority; return this; }
        public ServiceRequestBuilder otherMachine(String otherMachine) { this.otherMachine = otherMachine; return this; }
        public ServiceRequestBuilder otherOs(String otherOs) { this.otherOs = otherOs; return this; }
        public ServiceRequestBuilder documentType(String documentType) { this.documentType = documentType; return this; }
        public ServiceRequestBuilder networkType(String networkType) { this.networkType = networkType; return this; }
        public ServiceRequestBuilder additionalServices(String additionalServices) { this.additionalServices = additionalServices; return this; }
        public ServiceRequestBuilder notes(String notes) { this.notes = notes; return this; }
        public ServiceRequestBuilder submissionDate(LocalDateTime submissionDate) { this.submissionDate = submissionDate; return this; }
        public ServiceRequestBuilder lastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; return this; }

        public ServiceRequest build() {
            ServiceRequest sr = new ServiceRequest();
            sr.setId(id);
            sr.setUserName(userName);
            sr.setEmpId(empId);
            sr.setDivision(division);
            sr.setEmail(email);
            sr.setLocation(location);
            sr.setProjectNo(projectNo);
            sr.setMachineType(machineType);
            sr.setModelNo(modelNo);
            sr.setSerialNo(serialNo);
            sr.setPartNo(partNo);
            sr.setPirNo(pirNo);
            sr.setOperatingSystem(operatingSystem);
            sr.setServices(services);
            sr.setStatus(status);
            sr.setAssignedTo(assignedTo);
            sr.setPriority(priority);
            sr.setOtherMachine(otherMachine);
            sr.setOtherOs(otherOs);
            sr.setDocumentType(documentType);
            sr.setNetworkType(networkType);
            sr.setAdditionalServices(additionalServices);
            sr.setNotes(notes);
            sr.setSubmissionDate(submissionDate);
            sr.setLastUpdated(lastUpdated);
            return sr;
        }
    }
}
