package com.ps42.Student.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ps42.Student.model.Job;
import com.ps42.Student.model.StudentApplication;
import com.ps42.Student.service.Userjobservice;

@Controller
@RequestMapping("/user")
public class Userjobcontroller2 {

    @Autowired
    private Userjobservice userjobService;

    // GET method to display the job application form
    @GetMapping("/apply/{jobId}")
    public String apply(@PathVariable("jobId") Long jobId, Model model) {
        Job job = userjobService.getJobById(jobId); // Fetch job details
        if (job != null) {
            model.addAttribute("jobId", jobId); // Pass job ID to the template
            model.addAttribute("job", job);    // Pass job details to the template
            model.addAttribute("studentApplication", new StudentApplication()); // Empty form object
            return "apply";
        } else {
            return "redirect:/user/viewjobs"; // Redirect if job not found
        }
    }

    // POST method to handle job application submission
    @PostMapping("/apply")
    public String submitApplication(
            @RequestParam("jobId") Long jobId,
            @RequestParam("fullName") String fullName,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("address") String address,
            @RequestParam("coverLetter") String coverLetter,
            @RequestParam("resume") MultipartFile resumeFile) {
        
        Job job = userjobService.getJobById(jobId);
        if (job != null) {
            try {
                // Create and populate StudentApplication object
                StudentApplication studentApplication = new StudentApplication();
                studentApplication.setJobId(jobId);
                studentApplication.setFullName(fullName);
                studentApplication.setEmail(email);
                studentApplication.setPhoneNumber(phoneNumber);
                studentApplication.setAddress(address);
                studentApplication.setCoverLetter(coverLetter);

                // Convert and set resume as byte[]
                if (!resumeFile.isEmpty()) {
                    byte[] resumeData = resumeFile.getBytes();
                    if (resumeData.length > 65535) { // Assuming TEXT field in DB, adjust for MEDIUMTEXT/LONGTEXT
                        throw new IllegalArgumentException("Resume file size exceeds database column limit.");
                    }
                    studentApplication.setResume(resumeData);
                }

                // Save application
                userjobService.saveApplication(studentApplication);
                return "redirect:/user/applicationSuccess";
            } catch (Exception e) {
                e.printStackTrace();
                return "redirect:/user/apply/" + jobId + "?error=true";
            }
        } else {
            return "redirect:/user/viewjobs";
        }
    }
    
    
    @GetMapping("/viewapplications")
    public String viewApplications(Model model) {
        List<StudentApplication> applications = userjobService.getAllApplications();
        model.addAttribute("applications", applications);
        return "viewapplications";
    }


    @GetMapping("/download/{applicationId}")
    public ResponseEntity<byte[]> downloadResume(@PathVariable Long applicationId) {
        StudentApplication application = userjobService.getApplicationById(applicationId);

        if (application != null && application.getResume() != null) {
            try {
                byte[] fileData = application.getResume(); // Ensure this returns byte[]

                HttpHeaders headers = new HttpHeaders(null);
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.add("Content-Disposition", "attachment; filename=\"resume.pdf\"");

                return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
