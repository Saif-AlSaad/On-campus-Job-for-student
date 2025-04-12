package models;

public class Student {
    private static int nextId = 1;
    private int id;
    private String name;
    private String studentId;
    private String email;
    private String major;
    private String password;
    private String skills;

    public Student(String name, String studentId, String email, String major, String password, String skills) {
        this.id = nextId++;
        this.name = name;
        this.studentId = studentId;
        this.email = email;
        this.major = major;
        this.password = password;
        this.skills = skills;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s", name, studentId, major);
    }
}