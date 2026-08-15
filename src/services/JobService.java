package services;

import models.Job;

import java.util.ArrayList;
import java.util.List;

/**
 * Business service for job posting management.
 */
public class JobService {
    private final List<Job> jobs;
    private static JobService instance;

    private JobService() {
        jobs = new ArrayList<>();
        seedSampleData();
    }

    public static synchronized JobService getInstance() {
        if (instance == null) {
            instance = new JobService();
        }
        return instance;
    }

    private void seedSampleData() {
        jobs.add(new Job(
                "Library Assistant", "Library",
                "Help students find books and maintain library records",
                "Good organizational skills, friendly demeanor", 12.50, 15));
        jobs.add(new Job(
                "IT Helpdesk", "Computer Science",
                "Assist with basic computer issues in labs",
                "Basic computer knowledge, patience", 15.00, 20));
        jobs.add(new Job(
                "Research Assistant", "Biology",
                "Assist professors with research projects",
                "Biology major preferred, attention to detail", 18.00, 10));
    }

    public List<Job> getAllJobs() {
        return new ArrayList<>(jobs);
    }

    public List<Job> getOpenJobs() {
        List<Job> openJobs = new ArrayList<>();
        for (Job job : jobs) {
            if (job.isOpen()) {
                openJobs.add(job);
            }
        }
        return openJobs;
    }

    public Job getJobById(int id) {
        for (Job job : jobs) {
            if (job.getId() == id) {
                return job;
            }
        }
        return null;
    }

    public List<Job> searchOpenJobs(String keyword) {
        List<Job> results = new ArrayList<>();
        String query = keyword == null ? "" : keyword.trim().toLowerCase();

        for (Job job : getOpenJobs()) {
            if (query.isEmpty()
                    || containsIgnoreCase(job.getTitle(), query)
                    || containsIgnoreCase(job.getDepartment(), query)
                    || containsIgnoreCase(job.getDescription(), query)
                    || containsIgnoreCase(job.getRequirements(), query)) {
                results.add(job);
            }
        }
        return results;
    }

    public void addJob(Job job) {
        validateJob(job);
        jobs.add(job);
    }

    public boolean updateJob(Job updatedJob) {
        validateJob(updatedJob);

        for (int i = 0; i < jobs.size(); i++) {
            if (jobs.get(i).getId() == updatedJob.getId()) {
                jobs.set(i, updatedJob);
                return true;
            }
        }
        return false;
    }

    public boolean closeJob(int jobId) {
        Job job = getJobById(jobId);
        if (job == null) {
            return false;
        }
        job.setOpen(false);
        return true;
    }

    public boolean reopenJob(int jobId) {
        Job job = getJobById(jobId);
        if (job == null) {
            return false;
        }
        job.setOpen(true);
        return true;
    }

    private void validateJob(Job job) {
        if (job == null) {
            throw new IllegalArgumentException("Job cannot be null.");
        }
        if (isBlank(job.getTitle()) || isBlank(job.getDepartment())
                || isBlank(job.getDescription()) || isBlank(job.getRequirements())) {
            throw new IllegalArgumentException("Title, department, description, and requirements are required.");
        }
        if (job.getPayRate() < 0) {
            throw new IllegalArgumentException("Pay rate cannot be negative.");
        }
        if (job.getHoursPerWeek() <= 0) {
            throw new IllegalArgumentException("Hours per week must be greater than zero.");
        }
    }

    private boolean containsIgnoreCase(String value, String query) {
        return value != null && value.toLowerCase().contains(query);
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
