package com.jobcard.management.dto.response;

public class StatsResponse {

    private long totalJobs;
    private long pendingJobs;
    private long processingJobs;
    private long completedJobs;
    private long rejectedJobs;
    private long totalUsers;
    private long totalOfficers;

    public StatsResponse() {}

    public StatsResponse(long totalJobs, long pendingJobs, long processingJobs, long completedJobs, long rejectedJobs, long totalUsers, long totalOfficers) {
        this.totalJobs = totalJobs;
        this.pendingJobs = pendingJobs;
        this.processingJobs = processingJobs;
        this.completedJobs = completedJobs;
        this.rejectedJobs = rejectedJobs;
        this.totalUsers = totalUsers;
        this.totalOfficers = totalOfficers;
    }

    public long getTotalJobs() { return totalJobs; }
    public void setTotalJobs(long totalJobs) { this.totalJobs = totalJobs; }

    public long getPendingJobs() { return pendingJobs; }
    public void setPendingJobs(long pendingJobs) { this.pendingJobs = pendingJobs; }

    public long getProcessingJobs() { return processingJobs; }
    public void setProcessingJobs(long processingJobs) { this.processingJobs = processingJobs; }

    public long getCompletedJobs() { return completedJobs; }
    public void setCompletedJobs(long completedJobs) { this.completedJobs = completedJobs; }

    public long getRejectedJobs() { return rejectedJobs; }
    public void setRejectedJobs(long rejectedJobs) { this.rejectedJobs = rejectedJobs; }

    public long getTotalUsers() { return totalUsers; }
    public void setTotalUsers(long totalUsers) { this.totalUsers = totalUsers; }

    public long getTotalOfficers() { return totalOfficers; }
    public void setTotalOfficers(long totalOfficers) { this.totalOfficers = totalOfficers; }

    public static StatsResponseBuilder builder() { return new StatsResponseBuilder(); }

    public static class StatsResponseBuilder {
        private long totalJobs;
        private long pendingJobs;
        private long processingJobs;
        private long completedJobs;
        private long rejectedJobs;
        private long totalUsers;
        private long totalOfficers;

        public StatsResponseBuilder totalJobs(long totalJobs) { this.totalJobs = totalJobs; return this; }
        public StatsResponseBuilder pendingJobs(long pendingJobs) { this.pendingJobs = pendingJobs; return this; }
        public StatsResponseBuilder processingJobs(long processingJobs) { this.processingJobs = processingJobs; return this; }
        public StatsResponseBuilder completedJobs(long completedJobs) { this.completedJobs = completedJobs; return this; }
        public StatsResponseBuilder rejectedJobs(long rejectedJobs) { this.rejectedJobs = rejectedJobs; return this; }
        public StatsResponseBuilder totalUsers(long totalUsers) { this.totalUsers = totalUsers; return this; }
        public StatsResponseBuilder totalOfficers(long totalOfficers) { this.totalOfficers = totalOfficers; return this; }

        public StatsResponse build() {
            return new StatsResponse(totalJobs, pendingJobs, processingJobs, completedJobs, rejectedJobs, totalUsers, totalOfficers);
        }
    }
}
