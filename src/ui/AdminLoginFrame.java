package ui;

import javax.swing.*;
import java.awt.*;

public class AdminLoginFrame extends JFrame {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    public AdminLoginFrame() {
        UIStyles.prepareFrame(this, "CampusHire | Admin Login", 520, 430);

        JPanel root = UIStyles.pagePanel();
        JPanel card = UIStyles.card();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel brand = new JLabel("EMPLOYER / ADMIN PORTAL");
        brand.setFont(new Font("SansSerif", Font.BOLD, 13));
        brand.setForeground(UIStyles.NAVY);
        JLabel title = UIStyles.title("Manage campus hiring");
        JLabel subtitle = UIStyles.subtitle("Sign in to post jobs, review applications and manage students.");
        card.add(brand);
        card.add(Box.createVerticalStrut(8));
        card.add(title);
        card.add(Box.createVerticalStrut(6));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(24));

        card.add(UIStyles.label("Username"));
        JTextField usernameField = UIStyles.field(24);
        card.add(Box.createVerticalStrut(6));
        card.add(usernameField);
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
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password.", "Missing information", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
                new AdminDashboardFrame().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Sign-in failed", JOptionPane.ERROR_MESSAGE);
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
        SwingUtilities.invokeLater(() -> usernameField.requestFocusInWindow());
    }
}
