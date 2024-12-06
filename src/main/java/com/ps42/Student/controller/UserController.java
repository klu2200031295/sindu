package com.ps42.Student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ps42.Student.model.Job;
import com.ps42.Student.model.Register;
import com.ps42.Student.service.Userservice;
import com.ps42.Student.service.RegisterService;
import com.ps42.Student.service.Userjobservice;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private RegisterService registerService;
    @Autowired  
    private Userservice userservice;
   
    // Login handler
    @PostMapping("/student/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session, Model model) {
        Register register = userservice.validateUser(username, password);

        if (register != null) {
            session.setAttribute("username", register.getUsername());
            return "redirect:/home"; // Redirect to home.html in static folder
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "redirect:/error.html"; // Redirect to error.html if login fails
        }
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            model.addAttribute("username", username); // Add username to the model
            return "home"; // This will render the 'home.html' Thymeleaf template
        }
        return "redirect:/"; // Redirect to login if session is null
    }
    // Show the registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("register", new Register());
        return "register"; // Load the register.html without error messages
    }

    // Handle form submission
    @PostMapping("/register")
    public String registerUser(@ModelAttribute Register register, Model model) {
        // Check if the username already exists
        if (userservice.isUsernameTaken(register.getUsername())) {
            model.addAttribute("errorMessage", "Username already exists. Please choose a different one.");
            return "register"; // Return the form with the error message
        }

        // Check if passwords match
        if (!register.getPassword().equals(register.getConfirmPassword())) {
            model.addAttribute("passwordError", "Passwords do not match!");
            return "register";
        }

        // Save the user
        String result = userservice.saveUser(register);
        if ("Success".equals(result)) {
            return "redirect:/success.html"; // Redirect to a success page
        } else {
            model.addAttribute("errorMessage", "Registration failed. Please try again.");
            return "register";
        }
    }
    

    

    
}