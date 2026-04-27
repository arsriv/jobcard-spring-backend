package com.jobcard.management.service;

import com.jobcard.management.dto.request.OfficerCreateRequest;
import com.jobcard.management.dto.request.OfficerUpdateRequest;
import com.jobcard.management.dto.response.OfficerResponse;
import com.jobcard.management.enums.JobStatus;
import com.jobcard.management.exception.ResourceNotFoundException;
import com.jobcard.management.model.Officer;
import com.jobcard.management.model.ServiceRequest;
import com.jobcard.management.repository.OfficerRepository;
import com.jobcard.management.repository.ServiceRequestRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfficerService {

    private final OfficerRepository officerRepository;
    private final ServiceRequestRepository serviceRequestRepository;
    private final PasswordEncoder passwordEncoder;

    public OfficerService(OfficerRepository officerRepository,
                         ServiceRequestRepository serviceRequestRepository,
                         PasswordEncoder passwordEncoder) {
        this.officerRepository = officerRepository;
        this.serviceRequestRepository = serviceRequestRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<OfficerResponse> getAllOfficers() {
        return officerRepository.findAll().stream()
                .map(officer -> {
                    OfficerResponse response = OfficerResponse.fromOfficer(officer);
                    response.setAssignedJobs(serviceRequestRepository.countByAssignedTo(officer.getName()));
                    response.setCompletedJobs(serviceRequestRepository.countByAssignedToAndStatus(officer.getName(), JobStatus.COMPLETED));
                    return response;
                })
                .collect(Collectors.toList());
    }

    public OfficerResponse getOfficer(String id) {
        Officer officer = officerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Officer", "id", id));

        OfficerResponse response = OfficerResponse.fromOfficer(officer);
        response.setAssignedJobs(serviceRequestRepository.countByAssignedTo(officer.getName()));
        response.setCompletedJobs(serviceRequestRepository.countByAssignedToAndStatus(officer.getName(), JobStatus.COMPLETED));
        return response;
    }

    public OfficerResponse createOfficer(OfficerCreateRequest request) {
        if (officerRepository.existsByEmailId(request.getEmailId())) {
            throw new com.jobcard.management.exception.BadRequestException("Email already registered");
        }

        Officer officer = Officer.builder()
                .id(request.getId())
                .name(request.getName())
                .group(request.getGroup())
                .emailId(request.getEmailId())
                .specialization(request.getSpecialization())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        officer = officerRepository.save(officer);
        return OfficerResponse.fromOfficer(officer);
    }

    public OfficerResponse updateOfficer(String id, OfficerUpdateRequest request) {
        Officer officer = officerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Officer", "id", id));

        String oldName = officer.getName();

        if (request.getName() != null) {
            officer.setName(request.getName());
        }
        if (request.getGroup() != null) {
            officer.setGroup(request.getGroup());
        }
        if (request.getEmailId() != null) {
            if (!officer.getEmailId().equals(request.getEmailId()) &&
                    officerRepository.findByEmailId(request.getEmailId()).isPresent()) {
                throw new com.jobcard.management.exception.BadRequestException("Email already in use");
            }
            officer.setEmailId(request.getEmailId());
        }
        if (request.getSpecialization() != null) {
            officer.setSpecialization(request.getSpecialization());
        }

        officer = officerRepository.save(officer);

        if (request.getName() != null && !oldName.equals(request.getName())) {
            List<ServiceRequest> assignedJobs = serviceRequestRepository.findByAssignedToOrderBySubmissionDateDesc(oldName);
            for (ServiceRequest job : assignedJobs) {
                job.setAssignedTo(request.getName());
                serviceRequestRepository.save(job);
            }
        }

        OfficerResponse response = OfficerResponse.fromOfficer(officer);
        response.setAssignedJobs(serviceRequestRepository.countByAssignedTo(officer.getName()));
        response.setCompletedJobs(serviceRequestRepository.countByAssignedToAndStatus(officer.getName(), JobStatus.COMPLETED));
        return response;
    }

    public void deleteOfficer(String id) {
        Officer officer = officerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Officer", "id", id));

        List<ServiceRequest> assignedJobs = serviceRequestRepository.findByAssignedToOrderBySubmissionDateDesc(officer.getName());
        for (ServiceRequest job : assignedJobs) {
            job.setAssignedTo("Not Assigned");
            serviceRequestRepository.save(job);
        }

        officerRepository.deleteById(id);
    }
}
