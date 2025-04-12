package ui;

import models.Application;
import models.Job;
import models.Student;
import services.ApplicationService;
import services.JobService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewJobsFrame extends JFrame {
    private Student student;
    private boolean isAdminView;

    public ViewJobsFrame(Student student, boolean isAdminView) {
        this.student = student;
        this.isAdminView = isAdminView;

        setTitle(isAdminView ? "Manage Jobs" : "Available Jobs");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // Get jobs based on view type
        List<Job> jobs = isAdminView ?
                JobService.getInstance().getAllJobs() :
                JobService.getInstance().getOpenJobs();

        String[] columnNames = {"ID", "Title", "Department", "Pay Rate", "Hours/Week", "Status"};
        Object[][] data = new Object[jobs.size()][6];

        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            data[i][0] = job.getId();
            data[i][1] = job.getTitle();
            data[i][2] = job.getDepartment();
            data[i][3] = "$" + job.getPayRate();
            data[i][4] = job.getHoursPerWeek();
            data[i][5] = job.isOpen() ? "Open" : "Closed";
        }

        JTable jobsTable = new JTable(data, columnNames);
        jobsTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(jobsTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        // In the ViewJobsFrame constructor, update the applyBtn action listener (for student view):
        if (!isAdminView) {
            JButton applyBtn = new JButton("Apply for Selected Job");
            applyBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = jobsTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        int jobId = (int) jobsTable.getValueAt(selectedRow, 0);
                        Job job = JobService.getInstance().getJobById(jobId);

                        if (job != null && job.isOpen()) {
                            // Check if student already applied
                            boolean alreadyApplied = false;
                            for (Application app : ApplicationService.getInstance().getApplicationsByStudentId(student.getId())) {
                                if (app.getJobId() == jobId) {
                                    alreadyApplied = true;
                                    break;
                                }
                            }

                            if (alreadyApplied) {
                                JOptionPane.showMessageDialog(ViewJobsFrame.this,
                                        "You have already applied for this job.", "Info", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                String applicationDate = java.time.LocalDate.now().toString();
                                Application application = new Application(student.getId(), jobId, applicationDate);
                                ApplicationService.getInstance().addApplication(application);
                                JOptionPane.showMessageDialog(ViewJobsFrame.this,
                                        "Application submitted successfully!\nJob: " + job.getTitle(),
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(ViewJobsFrame.this,
                                    "This job is no longer available.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(ViewJobsFrame.this,
                                "Please select a job to apply.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            buttonPanel.add(applyBtn);
        } else {
            JButton applyBtn = new JButton("Apply for Selected Job");
            applyBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = jobsTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        int jobId = (int) jobsTable.getValueAt(selectedRow, 0);
                        // In a real application, we would create an application here
                        JOptionPane.showMessageDialog(ViewJobsFrame.this,
                                "Application submitted for job ID: " + jobId);
                    } else {
                        JOptionPane.showMessageDialog(ViewJobsFrame.this,
                                "Please select a job to apply.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            buttonPanel.add(applyBtn);
        }

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                if (isAdminView) {
                    new ui.AdminDashboardFrame().setVisible(true);
                } else {
                    new ui.StudentDashboardFrame(student).setVisible(true);
                }
            }
        });
        buttonPanel.add(backBtn);


        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
    }
}