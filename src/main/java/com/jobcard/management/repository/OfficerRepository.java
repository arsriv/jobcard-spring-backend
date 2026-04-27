package com.jobcard.management.repository;

import com.jobcard.management.model.Officer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfficerRepository extends MongoRepository<Officer, String> {
    Optional<Officer> findByEmailId(String emailId);
    Optional<Officer> findByName(String name);
    boolean existsByEmailId(String emailId);
}
