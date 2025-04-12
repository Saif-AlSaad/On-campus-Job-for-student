package ui;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create and show the main frame
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ui.MainFrame().setVisible(true);
            }
        });
    }
}