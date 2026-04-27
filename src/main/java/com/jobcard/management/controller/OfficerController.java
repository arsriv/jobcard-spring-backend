package com.jobcard.management.controller;

import com.jobcard.management.dto.request.JobUpdateRequest;
import com.jobcard.management.dto.response.ApiResponse;
import com.jobcard.management.dto.response.JobResponse;
import com.jobcard.management.security.UserPrincipal;
import com.jobcard.management.service.JobRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/officer")
public class OfficerController {

    private final JobRequestService jobRequestService;

    public OfficerController(JobRequestService jobRequestService) {
        this.jobRequestService = jobRequestService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<ApiResponse<List<JobResponse>>> getOfficerJobs(
            @AuthenticationPrincipal UserPrincipal principal) {

        List<JobResponse> jobs = jobRequestService.getOfficerJobs(principal.getName());
        return ResponseEntity.ok(ApiResponse.success("Jobs retrieved", jobs));
    }

    @GetMapping("/jobs/{jobId}")
    public ResponseEntity<ApiResponse<JobResponse>> getOfficerJob(
            @PathVariable String jobId,
            @AuthenticationPrincipal UserPrincipal principal) {

        JobResponse job = jobRequestService.getOfficerJob(jobId, principal.getName());
        return ResponseEntity.ok(ApiResponse.success("Job retrieved", job));
    }

    @PutMapping("/jobs/{jobId}")
    public ResponseEntity<ApiResponse<JobResponse>> updateOfficerJob(
            @PathVariable String jobId,
            @RequestBody JobUpdateRequest request,
            @AuthenticationPrincipal UserPrincipal principal) {

        JobResponse job = jobRequestService.updateOfficerJob(jobId, request, principal.getName());
        return ResponseEntity.ok(ApiResponse.success("Job updated", job));
    }
}
