package services;

import models.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private List<Student> students;
    private static StudentService instance;

    private StudentService() {
        students = new ArrayList<>();
        // Initialize with some sample students
        students.add(new Student("Saif Al Saad", "saif", "saif@university.edu", "Software Engineering", "saif123", "Java, Python"));
        students.add(new Student("Jane Smith", "S1002", "jane@university.edu", "Biology", "password123", "Lab skills"));
        students.add(new Student("Mike Johnson", "S1003", "mike@university.edu", "Business", "password123", "Excel, Communication"));
    }

    public static StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Student getStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public Student getStudentByCredentials(String studentId, String password) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId) && student.getPassword().equals(password)) {
                return student;
            }
        }
        return null;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void updateStudent(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == updatedStudent.getId()) {
                students.set(i, updatedStudent);
                break;
            }
        }
    }
    // Add this method to StudentService class
    public void deleteStudent(int studentId) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == studentId) {
                students.remove(i);
                break;
            }
        }
    }

}