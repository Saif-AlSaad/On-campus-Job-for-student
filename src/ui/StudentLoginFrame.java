package ui;

import models.Student;
import services.StudentService;

import javax.swing.*;
import java.awt.*;

public class StudentLoginFrame extends JFrame {
    private final JTextField studentIdField = new JTextField(15);
    private final JPasswordField passwordField = new JPasswordField(15);

    public StudentLoginFrame() {
        setTitle("Student Login");
        setSize(400, 270);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Student Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Student ID:"), gbc);

        gbc.gridx = 1;
        panel.add(studentIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Back to Main");

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        gbc.gridy = 4;
        panel.add(backButton, gbc);

        loginButton.addActionListener(e -> authenticate());
        passwordField.addActionListener(e -> authenticate());
        backButton.addActionListener(e -> returnToMain());

        add(panel);
    }

    private void authenticate() {
        String studentId = studentIdField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (studentId.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter both student ID and password.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        StudentService studentService = StudentService.getInstance();
        Student student = studentService.getStudentByCredentials(studentId, password);

        if (student == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Invalid student ID or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
            return;
        }

        JOptionPane.showMessageDialog(this, "Login successful!");
        new StudentDashboardFrame(student).setVisible(true);
        dispose();
    }

    private void returnToMain() {
        new MainFrame().setVisible(true);
        dispose();
    }
}
