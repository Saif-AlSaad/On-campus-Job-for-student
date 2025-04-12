package ui;

import services.StudentService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentLoginFrame extends JFrame {
    public StudentLoginFrame() {
        setTitle("Student Login");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Student Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        JLabel studentIdLabel = new JLabel("Student ID:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(studentIdLabel, gbc);

        JTextField studentIdField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(studentIdField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passwordField, gbc);

        JButton loginBtn = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(loginBtn, gbc);

        JButton backBtn = new JButton("Back to Main");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(backBtn, gbc);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = studentIdField.getText();
                String password = new String(passwordField.getPassword());

                StudentService studentService = StudentService.getInstance();
                if (studentService.getStudentByCredentials(studentId, password) != null) {
                    JOptionPane.showMessageDialog(StudentLoginFrame.this, "Login successful!");
                    new ui.StudentDashboardFrame(studentService.getStudentByCredentials(studentId, password)).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(StudentLoginFrame.this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ui.MainFrame().setVisible(true);
                dispose();
            }
        });

        add(panel);
    }
}