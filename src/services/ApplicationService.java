package services;

import models.Application;
import models.Job;

import java.util.ArrayList;
import java.util.List;

/**
 * Business service for student job applications.
 */
public class ApplicationService {
    private final List<Application> applications;
    private static ApplicationService instance;

    private ApplicationService() {
        applications = new ArrayList<>();
        seedSampleData();
    }

    public static synchronized ApplicationService getInstance() {
        if (instance == null) {
            instance = new ApplicationService();
        }
        return instance;
    }

    private void seedSampleData() {
        Application app1 = new Application(1, 1, "2023-05-01");
        app1.setNotes("Student has relevant experience in library work");
        applications.add(app1);

        Application app2 = new Application(2, 2, "2023-05-02");
        app2.setStatus("Approved");
        app2.setNotes("Excellent technical skills, hired for fall semester");
        applications.add(app2);

        Application app3 = new Application(3, 3, "2023-05-03");
        app3.setStatus("Rejected");
        app3.setNotes("Does not meet the required hours availability");
        applications.add(app3);
    }

    public List<Application> getAllApplications() {
        return new ArrayList<>(applications);
    }

    public List<Application> getApplicationsByStudentId(int studentId) {
        List<Application> result = new ArrayList<>();
        for (Application application : applications) {
            if (application.getStudentId() == studentId) {
                result.add(application);
            }
        }
        return result;
    }

    public List<Application> getApplicationsByJobId(int jobId) {
        List<Application> result = new ArrayList<>();
        for (Application application : applications) {
            if (application.getJobId() == jobId) {
                result.add(application);
            }
        }
        return result;
    }

    public boolean hasApplied(int studentId, int jobId) {
        for (Application application : applications) {
            if (application.getStudentId() == studentId && application.getJobId() == jobId) {
                return true;
            }
        }
        return false;
    }

    public void addApplication(Application application) {
        if (application == null) {
            throw new IllegalArgumentException("Application cannot be null.");
        }
        if (hasApplied(application.getStudentId(), application.getJobId())) {
            throw new IllegalArgumentException("Student has already applied for this job.");
        }
        applications.add(application);
    }

    public boolean updateApplication(Application updatedApplication) {
        if (updatedApplication == null) {
            throw new IllegalArgumentException("Application cannot be null.");
        }

        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).getId() == updatedApplication.getId()) {
                applications.set(i, updatedApplication);
                return true;
            }
        }
        return false;
    }

    public Application getApplicationById(int id) {
        for (Application application : applications) {
            if (application.getId() == id) {
                return application;
            }
        }
        return null;
    }

    /**
     * Produces a human-readable application summary for the UI layer.
     */
    public String getApplicationDetails(Application application) {
        if (application == null) {
            return "No application selected.";
        }

        Job job = JobService.getInstance().getJobById(application.getJobId());
        String jobTitle = job != null ? job.getTitle() : "Unknown job";

        return String.format(
                "Application #%d%nJob: %s%nStudent ID: %d%nStatus: %s%nApplied: %s%nNotes: %s",
                application.getId(),
                jobTitle,
                application.getStudentId(),
                application.getStatus(),
                application.getApplicationDate(),
                application.getNotes().isBlank() ? "None" : application.getNotes());
    }
}
