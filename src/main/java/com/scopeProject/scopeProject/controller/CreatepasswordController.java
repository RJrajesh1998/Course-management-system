package com.scopeProject.scopeProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.scopeProject.scopeProject.model.Createpassword;
import com.scopeProject.scopeProject.model.registerModel;
import com.scopeProject.scopeProject.repository.CreatepasswordRepository;
import com.scopeProject.scopeProject.repository.SignupRepository;
import com.scopeProject.scopeProject.repository.registerRepository;

@Controller
public class CreatepasswordController {

    @GetMapping("/createpassword")
    public String showPasswordPage() {
        return "createpassword";
    }
    @Autowired
    private SignupRepository signupRepository;


    @Autowired
    private registerRepository registerrepository;

    @PostMapping("/createpassword")
    public String setPassword(
            @RequestParam("password") String password,
            @RequestParam("confirmpassword") String confirmpassword,
            @RequestParam("email") String email,Createpassword cp, Model model) {

        try {
            registerModel user = registerrepository.findByEmail(email);

            if (user != null) {
                if (password.equals(confirmpassword)) {
                    user.setPassword(password);
                    registerrepository.save(user);
                    model.addAttribute("success", "Password generated successfully");
                    return "login";
                } else {
                    model.addAttribute("error", "Password and confirm password do not match");
                    return "createpassword";
                }
            } else {
                model.addAttribute("error", "User not found");
                return "redirect:/register";
            }
        } catch (IncorrectResultSizeDataAccessException ex) {
            // Log the exception for further analysis
            // logger.error("IncorrectResultSizeDataAccessException: {}", ex.getMessage());

            // Return a user-friendly error response
            model.addAttribute("error", "Unexpected error occurred");
            return "createpassword";
        }
    }
}
