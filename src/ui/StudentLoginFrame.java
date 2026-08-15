package ui;

import models.Student;
import services.StudentService;
import javax.swing.*;
import java.awt.*;

public class StudentLoginFrame extends JFrame {
    public StudentLoginFrame() {
        UIStyles.prepareFrame(this, "CampusHire | Student Login", 520, 430);

        JPanel root = UIStyles.pagePanel();
        JPanel card = UIStyles.card();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel brand = new JLabel("STUDENT PORTAL");
        brand.setFont(new Font("SansSerif", Font.BOLD, 13));
        brand.setForeground(UIStyles.BLUE);
        JLabel title = UIStyles.title("Welcome back");
        JLabel subtitle = UIStyles.subtitle("Sign in to discover and manage campus opportunities.");
        card.add(brand);
        card.add(Box.createVerticalStrut(8));
        card.add(title);
        card.add(Box.createVerticalStrut(6));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(24));

        card.add(UIStyles.label("Student ID"));
        JTextField studentIdField = UIStyles.field(24);
        card.add(Box.createVerticalStrut(6));
        card.add(studentIdField);
        card.add(Box.createVerticalStrut(15));
        card.add(UIStyles.label("Password"));
        JPasswordField passwordField = UIStyles.passwordField(24);
        card.add(Box.createVerticalStrut(6));
        card.add(passwordField);
        card.add(Box.createVerticalStrut(22));

        JButton loginBtn = UIStyles.primaryButton("Sign In");
        JButton backBtn = UIStyles.secondaryButton("Back");
        JPanel actions = new JPanel(new GridLayout(1, 2, 10, 0));
        actions.setOpaque(false);
        actions.add(loginBtn);
        actions.add(backBtn);
        card.add(actions);

        loginBtn.addActionListener(e -> {
            String studentId = studentIdField.getText().trim();
            String password = new String(passwordField.getPassword());
            if (studentId.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both Student ID and password.", "Missing information", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Student student = StudentService.getInstance().getStudentByCredentials(studentId, password);
            if (student != null) {
                new StudentDashboardFrame(student).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Student ID or password.", "Sign-in failed", JOptionPane.ERROR_MESSAGE);
                passwordField.setText("");
            }
        });
        passwordField.addActionListener(e -> loginBtn.doClick());
        backBtn.addActionListener(e -> {
            new MainFrame().setVisible(true);
            dispose();
        });

        root.add(card, BorderLayout.CENTER);
        add(root);
        SwingUtilities.invokeLater(() -> studentIdField.requestFocusInWindow());
    }
}
