package com.ps42.Student.service;

import com.ps42.Student.model.Register;
import com.ps42.Student.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    // Check if the username already exists
    public boolean isUsernameTaken(String username) {
        return registerRepository.findByUsername(username) != null;
    }

    // Save user if username is unique
    public String saveUser(Register register) {
        // Save the user if the username is unique
        registerRepository.save(register);
        return "Success"; // Indicate success
    }
}