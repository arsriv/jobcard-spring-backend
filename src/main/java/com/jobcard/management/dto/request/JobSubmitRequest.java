package com.jobcard.management.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class JobSubmitRequest {

    @NotBlank(message = "User name is required")
    private String userName;

    @NotBlank(message = "Employee ID is required")
    private String empId;

    @NotBlank(message = "Division is required")
    private String division;

    @NotBlank(message = "Email is required")
    private String email;

    private String location;
    private String projectNo;

    @NotBlank(message = "Machine type is required")
    private String machineType;

    private String modelNo;
    private String serialNo;
    private String partNo;
    private String pirNo;
    private String operatingSystem;

    @NotNull(message = "Services list is required")
    private List<String> services;

    private String otherMachine;
    private String otherOs;
    private String documentType;
    private String networkType;
    private String additionalServices;

    public JobSubmitRequest() {}

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
}
