package ui;

import models.Student;
import services.StudentService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class ManageStudentsFrame extends JFrame {
    public ManageStudentsFrame() {
        setTitle("Manage Students");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        StudentService studentService = StudentService.getInstance();
        List<Student> students = studentService.getAllStudents();

        String[] columnNames = {"ID", "Name", "Student ID", "Email", "Major"};
        Object[][] data = new Object[students.size()][5];

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            data[i][0] = student.getId();
            data[i][1] = student.getName();
            data[i][2] = student.getStudentId();
            data[i][3] = student.getEmail();
            data[i][4] = student.getMajor();
        }

        JTable studentsTable = new JTable(data, columnNames);
        studentsTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(studentsTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton viewDetailsBtn = new JButton("View Details");
        viewDetailsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int studentId = (int) studentsTable.getValueAt(selectedRow, 0);
                    Student student = studentService.getStudentById(studentId);
                    if (student != null) {
                        String details = String.format(
                                "Student Details:\n\n" +
                                        "Name: %s\n" +
                                        "Student ID: %s\n" +
                                        "Email: %s\n" +
                                        "Major: %s\n" +
                                        "Skills: %s",
                                student.getName(),
                                student.getStudentId(),
                                student.getEmail(),
                                student.getMajor(),
                                student.getSkills()
                        );
                        JOptionPane.showMessageDialog(ManageStudentsFrame.this,
                                details, "Student Details", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(ManageStudentsFrame.this,
                            "Please select a student first.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(viewDetailsBtn);

        JButton editBtn = new JButton("Edit Student");
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int studentId = (int) studentsTable.getValueAt(selectedRow, 0);
                    Student student = studentService.getStudentById(studentId);
                    if (student != null) {
                        new EditStudentFrame(student).setVisible(true);
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(ManageStudentsFrame.this,
                            "Please select a student first.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(editBtn);

        JButton addBtn = new JButton("Add New Student");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStudentFrame().setVisible(true);
                dispose();
            }
        });
        buttonPanel.add(addBtn);

        JButton deleteBtn = new JButton("Delete Student");
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int studentId = (int) studentsTable.getValueAt(selectedRow, 0);
                    int confirm = JOptionPane.showConfirmDialog(
                            ManageStudentsFrame.this,
                            "Are you sure you want to delete this student?",
                            "Confirm Deletion",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        // In a real app, we would need to check if student has applications first
                        studentService.deleteStudent(studentId);
                        JOptionPane.showMessageDialog(ManageStudentsFrame.this,
                                "Student deleted successfully!");
                        dispose();
                        new ManageStudentsFrame().setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(ManageStudentsFrame.this,
                            "Please select a student first.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(deleteBtn);

        JButton backBtn = new JButton("Back to Dashboard");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AdminDashboardFrame().setVisible(true);
            }
        });
        buttonPanel.add(backBtn);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
    }
}