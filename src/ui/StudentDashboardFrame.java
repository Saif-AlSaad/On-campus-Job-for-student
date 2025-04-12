package ui;

import models.Student;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentDashboardFrame extends JFrame {
    private Student student;

    public StudentDashboardFrame(Student student) {
        this.student = student;
        setTitle("Student Dashboard - " + student.getName());
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel welcomeLabel = new JLabel("Welcome, " + student.getName() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(welcomeLabel, gbc);

        JButton viewJobsBtn = new JButton("View Available Jobs");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(viewJobsBtn, gbc);

        JButton myApplicationsBtn = new JButton("My Applications");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(myApplicationsBtn, gbc);

        JButton updateProfileBtn = new JButton("Update Profile");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(updateProfileBtn, gbc);

        JButton logoutBtn = new JButton("Logout");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(logoutBtn, gbc);

        viewJobsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ui.ViewJobsFrame(student, false).setVisible(true);
            }
        });

        myApplicationsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement view applications functionality
                JOptionPane.showMessageDialog(StudentDashboardFrame.this,
                        "This feature would show your job applications.");
            }
        });

        updateProfileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement update profile functionality
                JOptionPane.showMessageDialog(StudentDashboardFrame.this,
                        "This feature would allow you to update your profile.");
            }
        });

        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ui.MainFrame().setVisible(true);
                dispose();
            }
        });
        // In the StudentDashboardFrame constructor, update the myApplicationsBtn action listener:
        myApplicationsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewApplicationsFrame(student, false).setVisible(true);
            }
        });
        updateProfileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateProfileFrame(student).setVisible(true);
                dispose();
            }
        });

        add(panel);
    }
}