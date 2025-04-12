package models;

public class Job {
    private static int nextId = 1;
    private int id;
    private String title;
    private String department;
    private String description;
    private String requirements;
    private double payRate;
    private int hoursPerWeek;
    private boolean isOpen;

    public Job(String title, String department, String description, String requirements,
               double payRate, int hoursPerWeek) {
        this.id = nextId++;
        this.title = title;
        this.department = department;
        this.description = description;
        this.requirements = requirements;
        this.payRate = payRate;
        this.hoursPerWeek = hoursPerWeek;
        this.isOpen = true;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getRequirements() { return requirements; }
    public void setRequirements(String requirements) { this.requirements = requirements; }
    public double getPayRate() { return payRate; }
    public void setPayRate(double payRate) { this.payRate = payRate; }
    public int getHoursPerWeek() { return hoursPerWeek; }
    public void setHoursPerWeek(int hoursPerWeek) { this.hoursPerWeek = hoursPerWeek; }
    public boolean isOpen() { return isOpen; }
    public void setOpen(boolean open) { isOpen = open; }

    @Override
    public String toString() {
        return String.format("%s (%s) - $%.2f/hr, %d hrs/week", title, department, payRate, hoursPerWeek);
    }
}