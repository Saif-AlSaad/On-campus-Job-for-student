package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboardFrame extends JFrame {
    public AdminDashboardFrame() {
        setTitle("Admin Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel welcomeLabel = new JLabel("Admin Dashboard");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(welcomeLabel, gbc);

        JButton postJobBtn = new JButton("Post New Job");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(postJobBtn, gbc);

        JButton manageJobsBtn = new JButton("Manage Jobs");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(manageJobsBtn, gbc);

        JButton viewApplicationsBtn = new JButton("View Applications");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(viewApplicationsBtn, gbc);

        JButton manageStudentsBtn = new JButton("Manage Students");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(manageStudentsBtn, gbc);

        JButton logoutBtn = new JButton("Logout");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(logoutBtn, gbc);

        postJobBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ui.JobPostingFrame().setVisible(true);
            }
        });

        manageJobsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ui.ViewJobsFrame(null, true).setVisible(true);
            }
        });

        viewApplicationsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AdminDashboardFrame.this,
                        "This feature would show all job applications.");
            }
        });

        manageStudentsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AdminDashboardFrame.this,
                        "This feature would allow managing student accounts.");
            }
        });

        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainFrame().setVisible(true);
                dispose();
            }
        });
        // In the AdminDashboardFrame constructor, update the viewApplicationsBtn action listener:
        // In the AdminDashboardFrame constructor, update the viewApplicationsBtn action listener:
        viewApplicationsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewApplicationsFrame(null, true).setVisible(true);
            }
        });

        manageStudentsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageStudentsFrame().setVisible(true);
                dispose();
            }
        });

        add(panel);
    }
}