package com.jobcard.management.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AdminSignupRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Employee ID is required")
    private String empId;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Group/Division is required")
    private String group;

    @NotBlank(message = "Password is required")
    private String password;

    public AdminSignupRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGroup() { return group; }
    public void setGroup(String group) { this.group = group; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
