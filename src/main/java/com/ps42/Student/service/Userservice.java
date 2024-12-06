package com.ps42.Student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps42.Student.model.Register;
import com.ps42.Student.repository.RegisterRepository;

@Service
public class Userservice {

    @Autowired
    private RegisterRepository registerRepository;

    // Validate user credentials for login
    public Register validateUser(String username, String password) {
        return registerRepository.findByUsernameAndPassword(username, password);
    }

    // Check if the username already exists in the database
    public boolean isUsernameTaken(String username) {
        return registerRepository.existsByUsername(username);
    }

    // Save the new user to the database
    public String saveUser(Register register) {
        try {
            registerRepository.save(register);  // Save the new user object to the repository
            return "Success";
        } catch (Exception e) {
            return "Failure";
        }
    }
}