package ui;

import models.Job;
import services.JobService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JobPostingFrame extends JFrame {
    public JobPostingFrame() {
        setTitle("Post New Job");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel titleLabel = new JLabel("Post New Job");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        JLabel jobTitleLabel = new JLabel("Job Title:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(jobTitleLabel, gbc);

        JTextField jobTitleField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(jobTitleField, gbc);

        JLabel departmentLabel = new JLabel("Department:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(departmentLabel, gbc);

        JTextField departmentField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(departmentField, gbc);

        JLabel descriptionLabel = new JLabel("Description:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(descriptionLabel, gbc);

        JTextArea descriptionArea = new JTextArea(3, 20);
        descriptionArea.setLineWrap(true);
        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(descriptionScroll, gbc);

        JLabel requirementsLabel = new JLabel("Requirements:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(requirementsLabel, gbc);

        JTextArea requirementsArea = new JTextArea(3, 20);
        requirementsArea.setLineWrap(true);
        JScrollPane requirementsScroll = new JScrollPane(requirementsArea);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(requirementsScroll, gbc);

        JLabel payRateLabel = new JLabel("Pay Rate ($/hr):");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(payRateLabel, gbc);

        JTextField payRateField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(payRateField, gbc);

        JLabel hoursLabel = new JLabel("Hours per Week:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(hoursLabel, gbc);

        JTextField hoursField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(hoursField, gbc);

        JButton postBtn = new JButton("Post Job");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(postBtn, gbc);

        JButton cancelBtn = new JButton("Cancel");
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(cancelBtn, gbc);

        postBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String title = jobTitleField.getText();
                    String department = departmentField.getText();
                    String description = descriptionArea.getText();
                    String requirements = requirementsArea.getText();
                    double payRate = Double.parseDouble(payRateField.getText());
                    int hours = Integer.parseInt(hoursField.getText());

                    Job job = new Job(title, department, description, requirements, payRate, hours);
                    JobService.getInstance().addJob(job);
                    JOptionPane.showMessageDialog(JobPostingFrame.this, "Job posted successfully!");
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JobPostingFrame.this,
                            "Please enter valid numbers for pay rate and hours.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(panel);
    }
}