package com.ps42.Student.service;

import com.ps42.Student.model.Job;
import com.ps42.Student.model.StudentApplication;
import com.ps42.Student.repository.JobRepository;
import com.ps42.Student.repository.StudentApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Userjobservice {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private StudentApplicationRepository applicationRepository;

    // Retrieve a list of all jobs (available to all users)
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // Retrieve a job by its ID
    public Job getJobById(Long jobId) {
        return jobRepository.findById(jobId).orElse(null); // Return job if found, else null
    }

    // Save a student's job application
    public void saveApplication(StudentApplication application) {
        applicationRepository.save(application); // Save the application to the database
    }

	public StudentApplication getApplicationById(Long applicationId) {
		
		return applicationRepository.findById(applicationId).orElse(null);
	}

	public List<StudentApplication> getAllApplications() {
		          return applicationRepository.findAll();
		    }
		
	
}
