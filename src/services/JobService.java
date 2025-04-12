package services;

import models.Job;
import java.util.ArrayList;
import java.util.List;

public class JobService {
    private List<Job> jobs;
    private static JobService instance;

    private JobService() {
        jobs = new ArrayList<>();
        // Initialize with some sample jobs
        jobs.add(new Job("Library Assistant", "Library", "Help students find books and maintain library records",
                "Good organizational skills, friendly demeanor", 12.50, 15));
        jobs.add(new Job("IT Helpdesk", "Computer Science", "Assist with basic computer issues in labs",
                "Basic computer knowledge, patience", 15.00, 20));
        jobs.add(new Job("Research Assistant", "Biology", "Assist professors with research projects",
                "Biology major preferred, attention to detail", 18.00, 10));
    }

    public static JobService getInstance() {
        if (instance == null) {
            instance = new JobService();
        }
        return instance;
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

    public void addJob(Job job) {
        jobs.add(job);
    }

    public void updateJob(Job updatedJob) {
        for (int i = 0; i < jobs.size(); i++) {
            if (jobs.get(i).getId() == updatedJob.getId()) {
                jobs.set(i, updatedJob);
                break;
            }
        }
    }

    public void closeJob(int jobId) {
        Job job = getJobById(jobId);
        if (job != null) {
            job.setOpen(false);
        }
    }
}