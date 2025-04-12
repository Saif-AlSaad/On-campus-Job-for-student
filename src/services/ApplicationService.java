package services;

import models.Application;
import java.util.ArrayList;
import java.util.List;

public class ApplicationService {




    private List<Application> applications;
    private static ApplicationService instance;

    private ApplicationService() {
        applications = new ArrayList<>();
        // Add some sample applications with notes
        Application app1 = new Application(1, 1, "2023-05-01");
        app1.setNotes("Student has relevant experience in library work");
        applications.add(app1);

        Application app2 = new Application(2, 2, "2023-05-02");
        app2.setStatus("Approved");
        app2.setNotes("Excellent technical skills, hired for fall semester");
        applications.add(app2);

        Application app3 = new Application(3, 3, "2023-05-03");
        app3.setStatus("Rejected");
        app3.setNotes("Doesn't meet the required hours availability");
        applications.add(app3);
    }

    public static ApplicationService getInstance() {
        if (instance == null) {
            instance = new ApplicationService();
        }
        return instance;
    }

    public List<Application> getAllApplications() {
        return new ArrayList<>(applications);
    }

    public List<Application> getApplicationsByStudentId(int studentId) {
        List<Application> studentApplications = new ArrayList<>();
        for (Application app : applications) {
            if (app.getStudentId() == studentId) {
                studentApplications.add(app);
            }
        }
        return studentApplications;
    }

    public List<Application> getApplicationsByJobId(int jobId) {
        List<Application> jobApplications = new ArrayList<>();
        for (Application app : applications) {
            if (app.getJobId() == jobId) {
                jobApplications.add(app);
            }
        }
        return jobApplications;
    }

    public void addApplication(Application application) {
        applications.add(application);
    }

    public void updateApplication(Application updatedApplication) {
        for (int i = 0; i < applications.size(); i++) {
            if (applications.get(i).getId() == updatedApplication.getId()) {
                applications.set(i, updatedApplication);
                break;
            }
        }
    }

    public Application getApplicationById(int id) {
        for (Application app : applications) {
            if (app.getId() == id) {
                return app;
            }
        }
        return null;
    }


    public String getApplicationDetails(Application app) {
        return "";
    }
}