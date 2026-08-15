# CampusHire — On-Campus Job Management System

CampusHire is a Java Swing desktop application that connects university students with on-campus employment opportunities. Students can browse positions and track applications, while administrators can manage jobs, applicants, and student records.

## Current architecture

- **Language:** Java
- **UI:** Java Swing
- **Architecture:** Model / Service / UI separation
- **Data layer:** In-memory collections (no external database yet)
- **Application entry point:** `src/ui/Main.java`

## Core capabilities

### Student portal
- Student authentication
- View open campus jobs
- Apply for available jobs
- Prevent duplicate applications for the same job
- Track application status
- View application details
- Update profile information

### Employer / admin portal
- Admin authentication
- Dashboard overview
- Create campus job postings
- Manage open and closed jobs
- Review applications
- Update application status and notes
- Manage student records

## Project structure

```text
src/
├── models/
│   ├── Application.java
│   ├── Job.java
│   └── Student.java
├── services/
│   ├── ApplicationService.java
│   ├── JobService.java
│   └── StudentService.java
└── ui/
    ├── Main.java
    ├── MainFrame.java
    ├── UIStyles.java
    ├── StudentLoginFrame.java
    ├── StudentDashboardFrame.java
    ├── ViewJobsFrame.java
    ├── ViewApplicationsFrame.java
    ├── UpdateProfileFrame.java
    ├── AdminLoginFrame.java
    ├── AdminDashboardFrame.java
    ├── JobPostingFrame.java
    └── ...
```

## Demo accounts

The current prototype uses in-memory demo credentials.

**Student**
- Student ID: `saif`
- Password: `saif123`

**Admin**
- Username: `admin`
- Password: `admin123`

> These credentials are intentionally part of the prototype. A production version should move authentication to a persistent database and store passwords using a secure password-hashing algorithm.

## Running the project

1. Clone the repository.
2. Open it in IntelliJ IDEA or another Java IDE.
3. Configure a compatible JDK.
4. Run `src/ui/Main.java`.

## Professional redesign

The current branch introduces a consistent visual system with:

- Centralized colors, typography, spacing, cards, buttons, and table styling
- Cleaner authentication screens
- Student dashboard with task-oriented cards
- Admin dashboard with operational statistics
- Improved validation and removal of duplicate action listeners
- More descriptive application copy and navigation labels

## Future production roadmap

1. Replace in-memory services with MySQL/PostgreSQL persistence.
2. Add role-based authentication and secure password hashing.
3. Add employer accounts instead of a single hardcoded administrator.
4. Add job search, filtering, sorting, and pagination.
5. Add application deadlines and eligibility rules.
6. Add audit logging and stronger validation.
7. Add automated unit and UI tests.
8. Add CI/CD and code-quality checks.

## License

This project is currently an academic/prototype project and does not yet include a formal open-source license.
