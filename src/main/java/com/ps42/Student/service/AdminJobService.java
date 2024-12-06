package com.ps42.Student.service;

import com.ps42.Student.model.Job;
import com.ps42.Student.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminJobService {

    @Autowired
    private JobRepository jobRepository;

    // Save the job (Admin-only operation)
    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    // Get job by ID (Admin access, but may be useful for updates)
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    // Delete job by ID (Admin-only operation)
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    // Get all jobs (Admin access)
    public List<Job> getAllJobs() {
        return jobRepository.findAll(); // Return the list of jobs
    }
}

