package ui;

import models.Student;
import services.StudentService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentFrame extends JFrame {
    public AddStudentFrame() {
        setTitle("Add New Student");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel titleLabel = new JLabel("Add New Student");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(nameField, gbc);

        JLabel studentIdLabel = new JLabel("Student ID:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(studentIdLabel, gbc);

        JTextField studentIdField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(studentIdField, gbc);

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(emailField, gbc);

        JLabel majorLabel = new JLabel("Major:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(majorLabel, gbc);

        JTextField majorField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(majorField, gbc);

        JLabel skillsLabel = new JLabel("Skills:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(skillsLabel, gbc);

        JTextArea skillsArea = new JTextArea(3, 20);
        skillsArea.setLineWrap(true);
        JScrollPane skillsScroll = new JScrollPane(skillsArea);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(skillsScroll, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(passwordField, gbc);

        JButton saveBtn = new JButton("Save Student");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(saveBtn, gbc);

        JButton cancelBtn = new JButton("Cancel");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        panel.add(cancelBtn, gbc);

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String studentId = studentIdField.getText();
                String email = emailField.getText();
                String major = majorField.getText();
                String skills = skillsArea.getText();
                String password = new String(passwordField.getPassword());

                if (name.isEmpty() || studentId.isEmpty() || email.isEmpty() || major.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(AddStudentFrame.this,
                            "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Student student = new Student(name, studentId, email, major, password, skills);
                StudentService.getInstance().addStudent(student);
                JOptionPane.showMessageDialog(AddStudentFrame.this,
                        "Student added successfully!");
                dispose();
                new ManageStudentsFrame().setVisible(true);
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ManageStudentsFrame().setVisible(true);
            }
        });

        add(panel);
    }
}