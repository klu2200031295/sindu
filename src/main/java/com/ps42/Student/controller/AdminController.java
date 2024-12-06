package com.ps42.Student.controller;

import com.ps42.Student.model.Register;
import com.ps42.Student.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/login")
    public String adminLogin(@RequestParam("username") String username,
                             @RequestParam("password") String password) {
        if (adminService.validateAdmin(username, password)) {
            return "redirect:/adminhome.html";  // Redirects to admin home page on successful login
        } else {
            return "redirect:/error.html";  // Redirects to error page on login failure
        }
    }

    // Endpoint to return student data as JSON
    @GetMapping("/api/admin/students")
    @ResponseBody
    public List<Register> getAllStudents() {
        return adminService.getAllStudents();
    }

    // Endpoint to show students page
    @GetMapping("/admin/students")
    public String showStudentsPage() {
        return "redirect:/students.html";  // Redirect to the static students.html page
    }
}
