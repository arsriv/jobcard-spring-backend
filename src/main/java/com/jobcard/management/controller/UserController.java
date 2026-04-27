package com.jobcard.management.controller;

import com.jobcard.management.dto.request.JobSubmitRequest;
import com.jobcard.management.dto.response.ApiResponse;
import com.jobcard.management.dto.response.JobResponse;
import com.jobcard.management.security.UserPrincipal;
import com.jobcard.management.service.JobRequestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final JobRequestService jobRequestService;

    public UserController(JobRequestService jobRequestService) {
        this.jobRequestService = jobRequestService;
    }

    @PostMapping("/jobs")
    public ResponseEntity<ApiResponse<JobResponse>> submitJob(
            @Valid @RequestBody JobSubmitRequest request,
            @AuthenticationPrincipal UserPrincipal principal) {

        JobResponse response = jobRequestService.submitJob(request);
        return ResponseEntity.ok(ApiResponse.success("Job submitted successfully", response));
    }

    @GetMapping("/jobs")
    public ResponseEntity<ApiResponse<List<JobResponse>>> getUserJobs(
            @AuthenticationPrincipal UserPrincipal principal) {

        List<JobResponse> jobs = jobRequestService.getUserJobs(principal.getId());
        return ResponseEntity.ok(ApiResponse.success("Jobs retrieved", jobs));
    }

    @GetMapping("/jobs/{jobId}")
    public ResponseEntity<ApiResponse<JobResponse>> getUserJob(
            @PathVariable String jobId,
            @AuthenticationPrincipal UserPrincipal principal) {

        JobResponse job = jobRequestService.getUserJob(jobId, principal.getId());
        return ResponseEntity.ok(ApiResponse.success("Job retrieved", job));
    }
}
