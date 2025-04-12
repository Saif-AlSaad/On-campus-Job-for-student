package ui;

import models.Application;
import models.Job;
import models.Student;
import services.ApplicationService;
import services.JobService;
import services.StudentService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewApplicationsFrame extends JFrame {
    private Student student;
    private boolean isAdminView;

    public ViewApplicationsFrame(Student student, boolean isAdminView) {
        this.student = student;
        this.isAdminView = isAdminView;

        setTitle(isAdminView ? "All Applications" : "My Applications");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        ApplicationService applicationService = ApplicationService.getInstance();
        List<Application> applications = isAdminView ?
                applicationService.getAllApplications() :
                applicationService.getApplicationsByStudentId(student.getId());

        // Replace the columnNames and data initialization with:
        String[] columnNames = {"ID", "Student", "Student ID", "Job Title", "Department", "Status", "Date"};
        Object[][] data = new Object[applications.size()][7];

        StudentService studentService = StudentService.getInstance();
        JobService jobService = JobService.getInstance();

        for (int i = 0; i < applications.size(); i++) {
            Application app = applications.get(i);
            student = studentService.getStudentById(app.getStudentId());
            Job job = jobService.getJobById(app.getJobId());

            data[i][0] = app.getId();
            data[i][1] = student != null ? student.getName() : "Unknown";
            data[i][2] = student != null ? student.getStudentId() : "Unknown";
            data[i][3] = job != null ? job.getTitle() : "Unknown";
            data[i][4] = job != null ? job.getDepartment() : "Unknown";
            data[i][5] = app.getStatus();
            data[i][6] = app.getApplicationDate();
        }

        JTable applicationsTable = new JTable(data, columnNames);
        applicationsTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(applicationsTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton viewDetailsBtn = new JButton("View Details");

        viewDetailsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = applicationsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int appId = (int) applicationsTable.getValueAt(selectedRow, 0);
                    Application app = applicationService.getApplicationById(appId);
                    if (app != null) {
                        // Get detailed information
                        Student student = StudentService.getInstance().getStudentById(app.getStudentId());
                        Job job = JobService.getInstance().getJobById(app.getJobId());

                        // Create a detailed message
                        StringBuilder details = new StringBuilder();
                        details.append("=== Application Details ===\n\n");
                        details.append(String.format("Application ID: %d\n", app.getId()));
                        details.append(String.format("Status: %s\n", app.getStatus()));
                        details.append(String.format("Date: %s\n\n", app.getApplicationDate()));

                        details.append("=== Student Information ===\n");
                        details.append(String.format("Name: %s\n", student.getName()));
                        details.append(String.format("Student ID: %s\n", student.getStudentId()));
                        details.append(String.format("Email: %s\n", student.getEmail()));
                        details.append(String.format("Major: %s\n", student.getMajor()));
                        details.append(String.format("Skills: %s\n\n", student.getSkills()));

                        details.append("=== Job Information ===\n");
                        details.append(String.format("Title: %s\n", job.getTitle()));
                        details.append(String.format("Department: %s\n", job.getDepartment()));
                        details.append(String.format("Pay Rate: $%.2f/hr\n", job.getPayRate()));
                        details.append(String.format("Hours/Week: %d\n", job.getHoursPerWeek()));
                        details.append(String.format("Status: %s\n\n", job.isOpen() ? "Open" : "Closed"));

                        details.append("=== Job Description ===\n");
                        details.append(job.getDescription() + "\n\n");

                        details.append("=== Job Requirements ===\n");
                        details.append(job.getRequirements() + "\n\n");

                        details.append("=== Application Notes ===\n");
                        details.append(app.getNotes().isEmpty() ? "No notes available" : app.getNotes());

                        // Show in a scrollable dialog
                        JTextArea textArea = new JTextArea(details.toString(), 15, 50);
                        textArea.setEditable(false);
                        textArea.setLineWrap(true);
                        textArea.setWrapStyleWord(true);
                        JScrollPane scrollPane = new JScrollPane(textArea);
                        JOptionPane.showMessageDialog(
                                ViewApplicationsFrame.this,
                                scrollPane,
                                "Application Details",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(ViewApplicationsFrame.this,
                            "Please select an application first.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        if (isAdminView) {
            JButton updateStatusBtn = new JButton("Update Status");
            updateStatusBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = applicationsTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        int appId = (int) applicationsTable.getValueAt(selectedRow, 0);
                        Application app = applicationService.getApplicationById(appId);
                        if (app != null) {
                            // Create a panel for status and notes update
                            JPanel panel = new JPanel(new GridLayout(0, 1));

                            // Status selection
                            JComboBox<String> statusCombo = new JComboBox<>(
                                    new String[]{"Pending", "Approved", "Rejected"});
                            statusCombo.setSelectedItem(app.getStatus());
                            panel.add(new JLabel("Status:"));
                            panel.add(statusCombo);

                            // Notes editing
                            JTextArea notesArea = new JTextArea(app.getNotes(), 5, 20);
                            notesArea.setLineWrap(true);
                            JScrollPane notesScroll = new JScrollPane(notesArea);
                            panel.add(new JLabel("Notes:"));
                            panel.add(notesScroll);

                            int result = JOptionPane.showConfirmDialog(
                                    ViewApplicationsFrame.this,
                                    panel,
                                    "Update Application",
                                    JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.PLAIN_MESSAGE);

                            if (result == JOptionPane.OK_OPTION) {
                                app.setStatus((String)statusCombo.getSelectedItem());
                                app.setNotes(notesArea.getText());
                                applicationService.updateApplication(app);
                                JOptionPane.showMessageDialog(ViewApplicationsFrame.this,
                                        "Application updated successfully!");
                                dispose();
                                new ViewApplicationsFrame(null, true).setVisible(true);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(ViewApplicationsFrame.this,
                                "Please select an application first.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            buttonPanel.add(updateStatusBtn);
        }

        JButton backBtn = new JButton("Back");
        Student finalStudent = student;
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                if (isAdminView) {
                    new AdminDashboardFrame().setVisible(true);
                } else {
                    new StudentDashboardFrame(finalStudent).setVisible(true);
                }
            }
        });
        buttonPanel.add(backBtn);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
    }
}