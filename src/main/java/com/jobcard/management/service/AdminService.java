package com.jobcard.management.service;

import com.jobcard.management.dto.response.StatsResponse;
import com.jobcard.management.enums.JobStatus;
import com.jobcard.management.repository.AdminRepository;
import com.jobcard.management.repository.OfficerRepository;
import com.jobcard.management.repository.ServiceRequestRepository;
import com.jobcard.management.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final ServiceRequestRepository serviceRequestRepository;
    private final UserRepository userRepository;
    private final OfficerRepository officerRepository;
    private final AdminRepository adminRepository;

    public AdminService(ServiceRequestRepository serviceRequestRepository,
                       UserRepository userRepository,
                       OfficerRepository officerRepository,
                       AdminRepository adminRepository) {
        this.serviceRequestRepository = serviceRequestRepository;
        this.userRepository = userRepository;
        this.officerRepository = officerRepository;
        this.adminRepository = adminRepository;
    }

    public StatsResponse getStats() {
        return StatsResponse.builder()
                .totalJobs(serviceRequestRepository.count())
                .pendingJobs(serviceRequestRepository.countByStatus(JobStatus.PENDING))
                .processingJobs(serviceRequestRepository.countByStatus(JobStatus.PROCESSING))
                .completedJobs(serviceRequestRepository.countByStatus(JobStatus.COMPLETED))
                .rejectedJobs(serviceRequestRepository.countByStatus(JobStatus.REJECTED))
                .totalUsers(userRepository.count())
                .totalOfficers(officerRepository.count())
                .build();
    }
}
