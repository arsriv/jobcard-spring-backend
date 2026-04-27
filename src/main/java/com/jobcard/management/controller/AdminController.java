package com.jobcard.management.controller;

import com.jobcard.management.dto.request.JobSubmitRequest;
import com.jobcard.management.dto.request.JobUpdateRequest;
import com.jobcard.management.dto.request.OfficerCreateRequest;
import com.jobcard.management.dto.request.OfficerUpdateRequest;
import com.jobcard.management.dto.request.UserUpdateRequest;
import com.jobcard.management.dto.response.*;
import com.jobcard.management.service.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final JobRequestService jobRequestService;
    private final UserService userService;
    private final OfficerService officerService;
    private final AdminService adminService;

    public AdminController(JobRequestService jobRequestService,
                          UserService userService,
                          OfficerService officerService,
                          AdminService adminService) {
        this.jobRequestService = jobRequestService;
        this.userService = userService;
        this.officerService = officerService;
        this.adminService = adminService;
    }

    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<StatsResponse>> getStats() {
        StatsResponse stats = adminService.getStats();
        return ResponseEntity.ok(ApiResponse.success("Stats retrieved", stats));
    }

    @GetMapping("/jobs")
    public ResponseEntity<ApiResponse<List<JobResponse>>> getAllJobs() {
        List<JobResponse> jobs = jobRequestService.getAllJobs();
        return ResponseEntity.ok(ApiResponse.success("Jobs retrieved", jobs));
    }

    @PostMapping("/jobs")
    public ResponseEntity<ApiResponse<JobResponse>> createJob(@Valid @RequestBody JobSubmitRequest request) {
        JobResponse job = jobRequestService.createJob(request);
        return ResponseEntity.ok(ApiResponse.success("Job created", job));
    }

    @GetMapping("/jobs/{jobId}")
    public ResponseEntity<ApiResponse<JobResponse>> getJob(@PathVariable String jobId) {
        JobResponse job = jobRequestService.getJob(jobId);
        return ResponseEntity.ok(ApiResponse.success("Job retrieved", job));
    }

    @PutMapping("/jobs/{jobId}")
    public ResponseEntity<ApiResponse<JobResponse>> updateJob(
            @PathVariable String jobId,
            @RequestBody JobUpdateRequest request) {
        JobResponse job = jobRequestService.updateJob(jobId, request);
        return ResponseEntity.ok(ApiResponse.success("Job updated", job));
    }

    @DeleteMapping("/jobs/{jobId}")
    public ResponseEntity<ApiResponse<Void>> deleteJob(@PathVariable String jobId) {
        jobRequestService.deleteJob(jobId);
        return ResponseEntity.ok(ApiResponse.success("Job deleted"));
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(ApiResponse.success("Users retrieved", users));
    }

    @PutMapping("/users/{empId}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(
            @PathVariable String empId,
            @RequestBody UserUpdateRequest request) {
        UserResponse user = userService.updateUser(empId, request);
        return ResponseEntity.ok(ApiResponse.success("User updated", user));
    }

    @DeleteMapping("/users/{empId}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable String empId) {
        userService.deleteUser(empId);
        return ResponseEntity.ok(ApiResponse.success("User deleted"));
    }

    @GetMapping("/officers")
    public ResponseEntity<ApiResponse<List<OfficerResponse>>> getAllOfficers() {
        List<OfficerResponse> officers = officerService.getAllOfficers();
        return ResponseEntity.ok(ApiResponse.success("Officers retrieved", officers));
    }

    @PostMapping("/officers")
    public ResponseEntity<ApiResponse<OfficerResponse>> createOfficer(
            @Valid @RequestBody OfficerCreateRequest request) {
        OfficerResponse officer = officerService.createOfficer(request);
        return ResponseEntity.ok(ApiResponse.success("Officer created", officer));
    }

    @PutMapping("/officers/{id}")
    public ResponseEntity<ApiResponse<OfficerResponse>> updateOfficer(
            @PathVariable String id,
            @RequestBody OfficerUpdateRequest request) {
        OfficerResponse officer = officerService.updateOfficer(id, request);
        return ResponseEntity.ok(ApiResponse.success("Officer updated", officer));
    }

    @DeleteMapping("/officers/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteOfficer(@PathVariable String id) {
        officerService.deleteOfficer(id);
        return ResponseEntity.ok(ApiResponse.success("Officer deleted"));
    }
}
