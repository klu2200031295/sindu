package com.ps42.Student.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps42.Student.model.Job;
import com.ps42.Student.service.Userjobservice;
@RestController
@RequestMapping("/user")
public class UserJobController {

    @Autowired
    private Userjobservice userjobService;

    @GetMapping("/jobs")
    public List<Job> listJobs() {
        return userjobService.getAllJobs();
    }
    
   
       
        
   

        
}