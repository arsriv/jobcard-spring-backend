package com.jobcard.management.service;

import com.jobcard.management.dto.request.UserUpdateRequest;
import com.jobcard.management.dto.response.UserResponse;
import com.jobcard.management.enums.JobStatus;
import com.jobcard.management.exception.ResourceNotFoundException;
import com.jobcard.management.model.User;
import com.jobcard.management.repository.ServiceRequestRepository;
import com.jobcard.management.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ServiceRequestRepository serviceRequestRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                      ServiceRequestRepository serviceRequestRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.serviceRequestRepository = serviceRequestRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> {
                    UserResponse response = UserResponse.fromUser(user);
                    response.setTotalJobs(serviceRequestRepository.countByEmpId(user.getId()));
                    response.setActiveJobs(serviceRequestRepository.countByEmpIdAndStatus(user.getId(), JobStatus.PENDING) +
                            serviceRequestRepository.countByEmpIdAndStatus(user.getId(), JobStatus.PROCESSING));
                    return response;
                })
                .collect(Collectors.toList());
    }

    public UserResponse getUser(String empId) {
        User user = userRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", empId));

        UserResponse response = UserResponse.fromUser(user);
        response.setTotalJobs(serviceRequestRepository.countByEmpId(user.getId()));
        response.setActiveJobs(serviceRequestRepository.countByEmpIdAndStatus(user.getId(), JobStatus.PENDING) +
                serviceRequestRepository.countByEmpIdAndStatus(user.getId(), JobStatus.PROCESSING));
        return response;
    }

    public UserResponse updateUser(String empId, UserUpdateRequest request) {
        User user = userRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", empId));

        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getEmail() != null) {
            if (!user.getEmail().equals(request.getEmail()) &&
                    userRepository.findByEmail(request.getEmail()).isPresent()) {
                throw new com.jobcard.management.exception.BadRequestException("Email already in use");
            }
            user.setEmail(request.getEmail());
        }
        if (request.getGroup() != null) {
            user.setGroup(request.getGroup());
        }
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        user = userRepository.save(user);

        UserResponse response = UserResponse.fromUser(user);
        response.setTotalJobs(serviceRequestRepository.countByEmpId(user.getId()));
        response.setActiveJobs(serviceRequestRepository.countByEmpIdAndStatus(user.getId(), JobStatus.PENDING) +
                serviceRequestRepository.countByEmpIdAndStatus(user.getId(), JobStatus.PROCESSING));
        return response;
    }

    public void deleteUser(String empId) {
        if (!userRepository.existsById(empId)) {
            throw new ResourceNotFoundException("User", "id", empId);
        }
        userRepository.deleteById(empId);
    }
}
