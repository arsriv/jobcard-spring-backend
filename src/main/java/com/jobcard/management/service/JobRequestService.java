package com.jobcard.management.service;

import com.jobcard.management.dto.request.JobSubmitRequest;
import com.jobcard.management.dto.request.JobUpdateRequest;
import com.jobcard.management.dto.response.JobResponse;
import com.jobcard.management.enums.JobStatus;
import com.jobcard.management.exception.ResourceNotFoundException;
import com.jobcard.management.model.ServiceRequest;
import com.jobcard.management.repository.ServiceRequestRepository;
import com.jobcard.management.util.RequestIdGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobRequestService {

    private final ServiceRequestRepository serviceRequestRepository;
    private final RequestIdGenerator requestIdGenerator;

    public JobRequestService(ServiceRequestRepository serviceRequestRepository,
                            RequestIdGenerator requestIdGenerator) {
        this.serviceRequestRepository = serviceRequestRepository;
        this.requestIdGenerator = requestIdGenerator;
    }

    public JobResponse submitJob(JobSubmitRequest request) {
        ServiceRequest serviceRequest = ServiceRequest.builder()
                .id(requestIdGenerator.generateRequestId())
                .userName(request.getUserName())
                .empId(request.getEmpId())
                .division(request.getDivision())
                .email(request.getEmail())
                .location(request.getLocation())
                .projectNo(request.getProjectNo())
                .machineType(request.getMachineType())
                .modelNo(request.getModelNo())
                .serialNo(request.getSerialNo())
                .partNo(request.getPartNo())
                .pirNo(request.getPirNo())
                .operatingSystem(request.getOperatingSystem())
                .services(request.getServices())
                .otherMachine(request.getOtherMachine())
                .otherOs(request.getOtherOs())
                .documentType(request.getDocumentType())
                .networkType(request.getNetworkType())
                .additionalServices(request.getAdditionalServices())
                .status(JobStatus.PENDING)
                .assignedTo("Not Assigned")
                .submissionDate(LocalDateTime.now())
                .lastUpdated(LocalDateTime.now())
                .build();

        serviceRequest = serviceRequestRepository.save(serviceRequest);
        return JobResponse.fromServiceRequest(serviceRequest);
    }

    public List<JobResponse> getUserJobs(String empId) {
        return serviceRequestRepository.findByEmpIdOrderBySubmissionDateDesc(empId)
                .stream()
                .map(JobResponse::fromServiceRequest)
                .collect(Collectors.toList());
    }

    public JobResponse getUserJob(String jobId, String empId) {
        ServiceRequest job = serviceRequestRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job", "id", jobId));

        if (!job.getEmpId().equals(empId)) {
            throw new ResourceNotFoundException("Job", "id", jobId);
        }

        return JobResponse.fromServiceRequest(job);
    }

    public List<JobResponse> getOfficerJobs(String officerName) {
        return serviceRequestRepository.findByAssignedToOrderBySubmissionDateDesc(officerName)
                .stream()
                .map(JobResponse::fromServiceRequest)
                .collect(Collectors.toList());
    }

    public JobResponse getOfficerJob(String jobId, String officerName) {
        ServiceRequest job = serviceRequestRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job", "id", jobId));

        if (!job.getAssignedTo().equals(officerName)) {
            throw new ResourceNotFoundException("Job", "id", jobId);
        }

        return JobResponse.fromServiceRequest(job);
    }

    public JobResponse updateOfficerJob(String jobId, JobUpdateRequest request, String officerName) {
        ServiceRequest job = serviceRequestRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job", "id", jobId));

        if (!job.getAssignedTo().equals(officerName)) {
            throw new ResourceNotFoundException("Job", "id", jobId);
        }

        if (request.getStatus() != null) {
            job.setStatus(request.getStatus());
        }
        if (request.getNotes() != null) {
            job.setNotes(request.getNotes());
        }
        job.setLastUpdated(LocalDateTime.now());

        job = serviceRequestRepository.save(job);
        return JobResponse.fromServiceRequest(job);
    }

    public List<JobResponse> getAllJobs() {
        return serviceRequestRepository.findAllByOrderBySubmissionDateDesc()
                .stream()
                .map(JobResponse::fromServiceRequest)
                .collect(Collectors.toList());
    }

    public JobResponse getJob(String jobId) {
        ServiceRequest job = serviceRequestRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job", "id", jobId));
        return JobResponse.fromServiceRequest(job);
    }

    public JobResponse createJob(JobSubmitRequest request) {
        ServiceRequest serviceRequest = ServiceRequest.builder()
                .id(requestIdGenerator.generateRequestId())
                .userName(request.getUserName())
                .empId(request.getEmpId())
                .division(request.getDivision())
                .email(request.getEmail())
                .location(request.getLocation())
                .projectNo(request.getProjectNo())
                .machineType(request.getMachineType())
                .modelNo(request.getModelNo())
                .serialNo(request.getSerialNo())
                .partNo(request.getPartNo())
                .pirNo(request.getPirNo())
                .operatingSystem(request.getOperatingSystem())
                .services(request.getServices())
                .otherMachine(request.getOtherMachine())
                .otherOs(request.getOtherOs())
                .documentType(request.getDocumentType())
                .networkType(request.getNetworkType())
                .additionalServices(request.getAdditionalServices())
                .status(JobStatus.PENDING)
                .assignedTo("Not Assigned")
                .submissionDate(LocalDateTime.now())
                .lastUpdated(LocalDateTime.now())
                .build();

        serviceRequest = serviceRequestRepository.save(serviceRequest);
        return JobResponse.fromServiceRequest(serviceRequest);
    }

    public JobResponse updateJob(String jobId, JobUpdateRequest request) {
        ServiceRequest job = serviceRequestRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job", "id", jobId));

        if (request.getStatus() != null) {
            job.setStatus(request.getStatus());
        }
        if (request.getPriority() != null) {
            job.setPriority(request.getPriority());
        }
        if (request.getAssignedTo() != null) {
            job.setAssignedTo(request.getAssignedTo());
        }
        if (request.getNotes() != null) {
            job.setNotes(request.getNotes());
        }
        job.setLastUpdated(LocalDateTime.now());

        job = serviceRequestRepository.save(job);
        return JobResponse.fromServiceRequest(job);
    }

    public void deleteJob(String jobId) {
        if (!serviceRequestRepository.existsById(jobId)) {
            throw new ResourceNotFoundException("Job", "id", jobId);
        }
        serviceRequestRepository.deleteById(jobId);
    }
}
