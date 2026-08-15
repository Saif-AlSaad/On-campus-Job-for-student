package services;

import models.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Application service responsible for student management and authentication.
 *
 * <p>The current implementation uses in-memory storage for the academic
 * prototype. The service API is intentionally isolated so a repository/database
 * implementation can be introduced later without changing the UI layer.</p>
 */
public class StudentService {
    private final List<Student> students;
    private static StudentService instance;

    private StudentService() {
        students = new ArrayList<>();
        seedSampleData();
    }

    public static synchronized StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    private void seedSampleData() {
        students.add(new Student(
                "Saif Al Saad", "saif", "saif@university.edu",
                "Software Engineering", "saif123", "Java, Python"));
        students.add(new Student(
                "Jane Smith", "S1002", "jane@university.edu",
                "Biology", "password123", "Lab skills"));
        students.add(new Student(
                "Mike Johnson", "S1003", "mike@university.edu",
                "Business", "password123", "Excel, Communication"));
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
        if (studentId == null || password == null || studentId.isBlank() || password.isBlank()) {
            return null;
        }

        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId.trim())
                    && student.getPassword().equals(password)) {
                return student;
            }
        }
        return null;
    }

    public boolean studentIdExists(String studentId) {
        if (studentId == null || studentId.isBlank()) {
            return false;
        }
        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId.trim())) {
                return true;
            }
        }
        return false;
    }

    public void addStudent(Student student) {
        validateStudent(student);
        if (studentIdExists(student.getStudentId())) {
            throw new IllegalArgumentException("A student with this ID already exists.");
        }
        students.add(student);
    }

    public boolean updateStudent(Student updatedStudent) {
        validateStudent(updatedStudent);

        for (int i = 0; i < students.size(); i++) {
            Student existing = students.get(i);
            if (existing.getId() == updatedStudent.getId()) {
                if (!existing.getStudentId().equalsIgnoreCase(updatedStudent.getStudentId())
                        && studentIdExists(updatedStudent.getStudentId())) {
                    throw new IllegalArgumentException("A student with this ID already exists.");
                }
                students.set(i, updatedStudent);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(int studentId) {
        return students.removeIf(student -> student.getId() == studentId);
    }

    private void validateStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        if (isBlank(student.getName()) || isBlank(student.getStudentId())
                || isBlank(student.getEmail()) || isBlank(student.getMajor())) {
            throw new IllegalArgumentException("Name, student ID, email, and major are required.");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
