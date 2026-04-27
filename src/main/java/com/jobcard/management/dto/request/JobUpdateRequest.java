package com.jobcard.management.dto.request;

import com.jobcard.management.enums.JobStatus;
import com.jobcard.management.enums.Priority;

public class JobUpdateRequest {

    private JobStatus status;
    private Priority priority;
    private String assignedTo;
    private String notes;

    public JobUpdateRequest() {}

    public JobStatus getStatus() { return status; }
    public void setStatus(JobStatus status) { this.status = status; }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
