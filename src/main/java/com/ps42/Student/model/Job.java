package com.ps42.Student.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    private String jobTitle;
    private String jobDescription;

    private String department;
    private String jobType;
    private LocalDate applicationDeadline;

    // New field: Eligibility criteria for students
    private String studentEligibilityCriteria;

    // Constructors
    public Job() {}

    public Job(String jobTitle, String jobDescription, String department, String jobType, LocalDate applicationDeadline, String studentEligibilityCriteria) {
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.department = department;
        this.jobType = jobType;
        this.applicationDeadline = applicationDeadline;
        this.studentEligibilityCriteria = studentEligibilityCriteria;
    }

    // Getters and Setters
    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public LocalDate getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(LocalDate applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    public String getStudentEligibilityCriteria() {
        return studentEligibilityCriteria;
    }

    public void setStudentEligibilityCriteria(String studentEligibilityCriteria) {
        this.studentEligibilityCriteria = studentEligibilityCriteria;
    }
}
