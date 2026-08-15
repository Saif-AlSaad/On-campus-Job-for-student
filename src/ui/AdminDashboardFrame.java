package ui;

import services.JobService;
import services.StudentService;
import services.ApplicationService;
import javax.swing.*;
import java.awt.*;

public class AdminDashboardFrame extends JFrame {
    public AdminDashboardFrame() {
        UIStyles.prepareFrame(this, "CampusHire | Admin Dashboard", 900, 620);

        JPanel root = UIStyles.pagePanel();
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JPanel heading = new JPanel();
        heading.setLayout(new BoxLayout(heading, BoxLayout.Y_AXIS));
        heading.setOpaque(false);
        heading.add(UIStyles.title("Hiring Dashboard"));
        heading.add(Box.createVerticalStrut(6));
        heading.add(UIStyles.subtitle("Manage campus opportunities, applicants and student records."));
        header.add(heading, BorderLayout.WEST);
        JButton logoutBtn = UIStyles.secondaryButton("Logout");
        header.add(logoutBtn, BorderLayout.EAST);

        JPanel stats = new JPanel(new GridLayout(1, 3, 14, 0));
        stats.setOpaque(false);
        stats.add(statCard("Open Jobs", String.valueOf(JobService.getInstance().getOpenJobs().size())));
        stats.add(statCard("Students", String.valueOf(StudentService.getInstance().getAllStudents().size())));
        stats.add(statCard("Applications", String.valueOf(ApplicationService.getInstance().getAllApplications().size())));

        JPanel actions = new JPanel(new GridLayout(2, 2, 16, 16));
        actions.setOpaque(false);
        actions.add(actionCard("Post a Job", "Create a new campus position with pay, hours and requirements.", "Post New Job", () -> new JobPostingFrame().setVisible(true)));
        actions.add(actionCard("Manage Jobs", "Review open and closed positions and manage job listings.", "Manage Jobs", () -> new ViewJobsFrame(null, true).setVisible(true)));
        actions.add(actionCard("Applications", "Review applicants, inspect details and update application status.", "Review Applications", () -> new ViewApplicationsFrame(null, true).setVisible(true)));
        actions.add(actionCard("Students", "Manage registered student profiles and account information.", "Manage Students", () -> {
            new ManageStudentFrame().setVisible(true);
            dispose();
        }));

        logoutBtn.addActionListener(e -> {
            new MainFrame().setVisible(true);
            dispose();
        });

        root.add(header, BorderLayout.NORTH);
        root.add(stats, BorderLayout.CENTER);
        JPanel lower = new JPanel(new BorderLayout());
        lower.setOpaque(false);
        lower.add(actions, BorderLayout.CENTER);
        root.add(lower, BorderLayout.SOUTH);
        add(root);
    }

    private JPanel statCard(String name, String value) {
        JPanel card = UIStyles.card();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        JLabel number = UIStyles.title(value);
        number.setFont(new Font("SansSerif", Font.BOLD, 28));
        JLabel label = UIStyles.subtitle(name);
        card.add(number);
        card.add(Box.createVerticalStrut(4));
        card.add(label);
        return card;
    }

    private JPanel actionCard(String title, String description, String buttonText, Runnable action) {
        JPanel card = UIStyles.card();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        JLabel titleLabel = UIStyles.title(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(UIStyles.subtitle("<html><div style='width:300px'>" + description + "</div></html>"));
        card.add(Box.createVerticalGlue());
        JButton button = UIStyles.primaryButton(buttonText);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.addActionListener(e -> action.run());
        card.add(button);
        return card;
    }
}
