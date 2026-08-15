package ui;

import models.Student;
import javax.swing.*;
import java.awt.*;

public class StudentDashboardFrame extends JFrame {
    private final Student student;

    public StudentDashboardFrame(Student student) {
        this.student = student;
        UIStyles.prepareFrame(this, "CampusHire | Student Dashboard", 820, 560);

        JPanel root = UIStyles.pagePanel();
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JPanel heading = new JPanel();
        heading.setLayout(new BoxLayout(heading, BoxLayout.Y_AXIS));
        heading.setOpaque(false);
        JLabel title = UIStyles.title("Student Dashboard");
        JLabel subtitle = UIStyles.subtitle("Welcome back, " + student.getName() + ". Manage your campus job search from here.");
        heading.add(title);
        heading.add(Box.createVerticalStrut(6));
        heading.add(subtitle);
        header.add(heading, BorderLayout.WEST);

        JPanel profile = UIStyles.card();
        profile.setLayout(new BoxLayout(profile, BoxLayout.Y_AXIS));
        JLabel profileTitle = UIStyles.label(student.getMajor());
        JLabel profileMeta = UIStyles.subtitle(student.getStudentId() + " • " + student.getEmail());
        profile.add(profileTitle);
        profile.add(Box.createVerticalStrut(5));
        profile.add(profileMeta);
        header.add(profile, BorderLayout.EAST);

        JPanel cards = new JPanel(new GridLayout(2, 2, 16, 16));
        cards.setOpaque(false);
        cards.add(actionCard("Find Jobs", "Browse currently open campus positions.", "Browse Jobs", UIStyles.BLUE, () -> new ViewJobsFrame(student, false).setVisible(true)));
        cards.add(actionCard("Applications", "Track your submitted applications and status.", "My Applications", UIStyles.SUCCESS, () -> new ViewApplicationsFrame(student, false).setVisible(true)));
        cards.add(actionCard("Profile", "Keep your contact details and skills up to date.", "Update Profile", UIStyles.NAVY, () -> {
            new UpdateProfileFrame(student).setVisible(true);
            dispose();
        }));
        cards.add(actionCard("Session", "Return to the secure application entry point.", "Logout", UIStyles.DANGER, () -> {
            new MainFrame().setVisible(true);
            dispose();
        }));

        root.add(header, BorderLayout.NORTH);
        root.add(cards, BorderLayout.CENTER);
        add(root);
    }

    private JPanel actionCard(String title, String description, String buttonText, Color accent, Runnable action) {
        JPanel card = UIStyles.card();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        JLabel titleLabel = UIStyles.title(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 19));
        JLabel descriptionLabel = UIStyles.subtitle("<html><div style='width:260px'>" + description + "</div></html>");
        JButton button = UIStyles.primaryButton(buttonText);
        button.setBackground(accent);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.addActionListener(e -> action.run());
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(descriptionLabel);
        card.add(Box.createVerticalGlue());
        card.add(button);
        return card;
    }
}
