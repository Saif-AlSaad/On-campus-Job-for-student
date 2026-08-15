package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        UIStyles.prepareFrame(this, "CampusHire | On-Campus Jobs", 720, 480);

        JPanel root = UIStyles.pagePanel();
        JPanel hero = UIStyles.card();
        hero.setLayout(new BoxLayout(hero, BoxLayout.Y_AXIS));

        JLabel brand = new JLabel("CAMPUSHIRE");
        brand.setFont(new Font("SansSerif", Font.BOLD, 14));
        brand.setForeground(UIStyles.BLUE);
        brand.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel title = UIStyles.title("Find opportunities on campus.");
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel subtitle = UIStyles.subtitle("A simple, professional workspace for students and campus employers.");
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        hero.add(brand);
        hero.add(Box.createVerticalStrut(10));
        hero.add(title);
        hero.add(Box.createVerticalStrut(8));
        hero.add(subtitle);
        hero.add(Box.createVerticalStrut(28));

        JPanel actions = new JPanel(new GridLayout(1, 2, 14, 0));
        actions.setOpaque(false);
        JButton studentLoginBtn = UIStyles.primaryButton("Student Portal");
        JButton adminLoginBtn = UIStyles.secondaryButton("Employer / Admin Portal");
        actions.add(studentLoginBtn);
        actions.add(adminLoginBtn);
        hero.add(actions);

        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(false);
        JLabel footerText = UIStyles.subtitle("CampusHire • Student employment management system");
        footer.add(footerText, BorderLayout.WEST);

        studentLoginBtn.addActionListener(e -> {
            new StudentLoginFrame().setVisible(true);
            dispose();
        });
        adminLoginBtn.addActionListener(e -> {
            new AdminLoginFrame().setVisible(true);
            dispose();
        });

        root.add(hero, BorderLayout.CENTER);
        root.add(footer, BorderLayout.SOUTH);
        add(root);
    }
}
