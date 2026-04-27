package com.jobcard.management.dto.response;

import com.jobcard.management.enums.JobStatus;
import com.jobcard.management.enums.Priority;
import com.jobcard.management.model.ServiceRequest;

import java.time.LocalDateTime;
import java.util.List;

public class JobResponse {

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
    private JobStatus status;
    private String assignedTo;
    private Priority priority;
    private String otherMachine;
    private String otherOs;
    private String documentType;
    private String networkType;
    private String additionalServices;
    private String notes;
    private LocalDateTime submissionDate;
    private LocalDateTime lastUpdated;

    public JobResponse() {}

    public static JobResponse fromServiceRequest(ServiceRequest sr) {
        JobResponse response = new JobResponse();
        response.setId(sr.getId());
        response.setUserName(sr.getUserName());
        response.setEmpId(sr.getEmpId());
        response.setDivision(sr.getDivision());
        response.setEmail(sr.getEmail());
        response.setLocation(sr.getLocation());
        response.setProjectNo(sr.getProjectNo());
        response.setMachineType(sr.getMachineType());
        response.setModelNo(sr.getModelNo());
        response.setSerialNo(sr.getSerialNo());
        response.setPartNo(sr.getPartNo());
        response.setPirNo(sr.getPirNo());
        response.setOperatingSystem(sr.getOperatingSystem());
        response.setServices(sr.getServices());
        response.setStatus(sr.getStatus());
        response.setAssignedTo(sr.getAssignedTo());
        response.setPriority(sr.getPriority());
        response.setOtherMachine(sr.getOtherMachine());
        response.setOtherOs(sr.getOtherOs());
        response.setDocumentType(sr.getDocumentType());
        response.setNetworkType(sr.getNetworkType());
        response.setAdditionalServices(sr.getAdditionalServices());
        response.setNotes(sr.getNotes());
        response.setSubmissionDate(sr.getSubmissionDate());
        response.setLastUpdated(sr.getLastUpdated());
        return response;
    }

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
}
