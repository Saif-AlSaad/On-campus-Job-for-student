package models;

public class Application {
    private static int nextId = 1;
    private int id;
    private int studentId;
    private int jobId;
    private String status; // "Pending", "Approved", "Rejected"
    private String applicationDate;
    private String notes;

    public Application(int studentId, int jobId, String applicationDate) {
        this.id = nextId++;
        this.studentId = studentId;
        this.jobId = jobId;
        this.status = "Pending";
        this.applicationDate = applicationDate;
        this.notes = "";
    }

    // Getters and Setters
    public int getId() { return id; }
    public int getStudentId() { return studentId; }
    public int getJobId() { return jobId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getApplicationDate() { return applicationDate; }
    public void setApplicationDate(String applicationDate) { this.applicationDate = applicationDate; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}