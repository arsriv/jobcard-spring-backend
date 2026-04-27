package com.jobcard.management.repository;

import com.jobcard.management.enums.JobStatus;
import com.jobcard.management.model.ServiceRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRequestRepository extends MongoRepository<ServiceRequest, String> {
    List<ServiceRequest> findByEmpIdOrderBySubmissionDateDesc(String empId);
    List<ServiceRequest> findByAssignedToOrderBySubmissionDateDesc(String officerName);
    List<ServiceRequest> findAllByOrderBySubmissionDateDesc();
    long countByStatus(JobStatus status);
    long countByEmpId(String empId);
    long countByEmpIdAndStatus(String empId, JobStatus status);
    long countByAssignedTo(String officerName);
    long countByAssignedToAndStatus(String officerName, JobStatus status);
    
    @Aggregation(pipeline = {
        "{ $group: { _id: '$empId' } }",
        "{ $count: 'count' }"
    })
    long countDistinctEmpId();
}
