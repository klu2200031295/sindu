package com.ps42.Student.service;

import com.ps42.Student.model.Admin;
import com.ps42.Student.model.Register;
import com.ps42.Student.repository.AdminRepository;
import com.ps42.Student.repository.RegisterRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // Validate Admin Credentials
    public boolean validateAdmin(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);
        return admin != null && admin.getPassword().equals(password);
    }
   

    @Autowired
    private RegisterRepository registerRepository;

    // Method to fetch all students
    public List<Register> getAllStudents() {
        return registerRepository.findAll();
    }
}
