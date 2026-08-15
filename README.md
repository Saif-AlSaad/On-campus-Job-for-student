# On-Campus Jobs for Students

A Java Swing desktop application that connects students with on-campus employment opportunities. Students can create profiles, browse available jobs, submit applications, and track application status, while administrators can manage students, job postings, and applications.

## Project Overview

This project demonstrates a small-scale job management system built with **Java**, **Swing**, object-oriented design, and a service-layer architecture. The current implementation uses in-memory collections, making it suitable for academic demonstration and as a foundation for a future database-backed application.

## Core Features

### Student
- Student login and profile management
- Browse open on-campus jobs
- View job descriptions, requirements, pay rate, and weekly hours
- Apply for available positions
- View submitted applications and their status

### Administrator
- Secure admin login flow for the prototype
- Add and manage student records
- Create and manage job postings
- Review student applications
- Update application status and notes
- Close job postings when positions are no longer available

## Architecture

The codebase follows a lightweight layered structure:

```text
src/
├── models/      # Domain entities: Student, Job, Application
├── services/    # Business logic and in-memory data management
└── ui/          # Java Swing desktop interfaces
```

The separation between models, services, and UI keeps business logic out of the presentation layer and provides a cleaner path toward a database-backed implementation.

## Technology Stack

- **Language:** Java
- **UI:** Java Swing
- **Architecture:** Layered architecture / service layer
- **Data storage:** In-memory collections (prototype)
- **IDE:** IntelliJ IDEA compatible
- **Version control:** Git + GitHub

## Running the Project

1. Clone the repository.
2. Open the project in IntelliJ IDEA or another Java IDE.
3. Ensure a compatible JDK is configured.
4. Mark `src` as the source root if your IDE does not detect it automatically.
5. Run `src/ui/Main.java`.

## Important Prototype Limitation

The application currently stores data in memory. Restarting the application resets students, jobs, and applications. For production use, the next major step should be replacing the in-memory services with a relational database and a repository/data-access layer.

## Recommended Production Roadmap

- Add MySQL/PostgreSQL persistence using JDBC or JPA/Hibernate
- Hash passwords instead of storing plaintext credentials
- Introduce role-based authentication and authorization
- Add input validation and centralized exception handling
- Add automated unit and integration tests
- Add search, filtering, sorting, and pagination for jobs
- Add application deadline and job-posting lifecycle management
- Add audit logging for administrative actions
- Improve accessibility and responsive UI behavior
- Add CI checks for build and tests

## Project Status

**Status:** Academic prototype / actively refactorable foundation

The project is intentionally structured so the current desktop application can evolve into a production-style student employment platform without rewriting the entire domain model.
