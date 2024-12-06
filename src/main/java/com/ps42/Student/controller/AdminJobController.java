package com.ps42.Student.controller;

import com.ps42.Student.model.Job;
import com.ps42.Student.service.AdminJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminJobController {

    @Autowired
    private AdminJobService adminJobService;

    // Display job creation form
    @GetMapping("/jobs/create")
    public String showJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "job-form";
    }

    // Save the job and redirect to success page
    @PostMapping("/jobs/create")
    public String createJob(@ModelAttribute("job") Job job) {
        adminJobService.saveJob(job);
        return "redirect:/jobpostingsuccessful.html"; // Redirect to success page after posting
    }

    // List jobs for the HTML view
    @GetMapping("/jobs")
    public String listJobs(Model model) {
        model.addAttribute("jobs", adminJobService.getAllJobs());
        return "admin-view-jobs";  // Name of the HTML view template (no .html suffix needed)
    }

    // List jobs as JSON for AJAX fetching
    @GetMapping("/api/jobs")
    @ResponseBody
    public List<Job> listJobsAsJson() {
        return adminJobService.getAllJobs(); // This will return the list of jobs to the frontend as JSON
    }


    // Delete job by ID
    @GetMapping("/jobs/delete/{id}")
    public String deleteJob(@PathVariable("id") Long id) {
        adminJobService.deleteJob(id);
        return "redirect:/admin/jobs"; // Redirect back to job list after deletion
    }

    // Display job update form
    @GetMapping("/jobs/edit/{id}")
    public String editJob(@PathVariable("id") Long id, Model model) {
        Job job = adminJobService.getJobById(id);
        if (job != null) {
            model.addAttribute("job", job);
            return "job-form"; // Display job form for editing
        }
        return "redirect:/admin/jobs"; // Redirect back to job list if job not found
    }
}
