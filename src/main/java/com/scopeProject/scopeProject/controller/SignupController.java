package com.scopeProject.scopeProject.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

import com.scopeProject.scopeProject.model.SignupModel;
import com.scopeProject.scopeProject.model.registerModel;
import com.scopeProject.scopeProject.repository.SignupRepository;
import com.scopeProject.scopeProject.repository.registerRepository;
import com.scopeProject.scopeProject.service.SignupService;

@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;

    @Autowired
    private registerRepository registerrepository;

//    @Autowired
//    private SignupService signupService;

    @Autowired
    private JavaMailSender mailSender;
    
    private final CookController cookController ;
    
    public SignupController (CookController cookController) {
    	this.cookController=cookController;
    }

    @GetMapping("/signup")
    
    public String showFirstTimeLogin( SignupModel signup) {
    	
        return "signup";
    }
    	
    @PostMapping("/signup")
    public String signup(Model model, SignupModel signup, String email) throws MessagingException {
        registerModel existingStudent = registerrepository.findByEmail(email);
        String sr = signup.getEmail();

        if (existingStudent != null) {
            if (existingStudent.getPassword() == null) {
                if (existingStudent.getEmail().equals(signup.getEmail())) {
                    String newOtp = generateRandomOtp();
                    signup.setOtp(newOtp);
                    signupRepository.save(signup);
                    sendMail(sr, newOtp);
                    model.addAttribute("email", existingStudent.getEmail());
                    return "verifyPage";
                } else {
                    model.addAttribute("error", "Invalid email");
                    return "signup";
                }
            } else {
                model.addAttribute("error", "Password is already created");
                return "signup";
            }
        } else {
            model.addAttribute("error", "Email doesn't exist, please register first");
            return "redirect:/register";
        }
    }

   
    
    
    
    
    @PostMapping("/verifyPage")
    public String verifyOtp(@RequestParam("email") String email,
                            @RequestParam("otp") String enteredOTP,
                            Model model) {
       SignupModel student = signupRepository.findByEmail(email);
        if (student != null && student.getOtp() != null && student.getOtp().equals(enteredOTP)) {
            student.setVerified(true);
            signupRepository.save(student);
            model.addAttribute("email", email);
            return "createpassword";
        } else {
            model.addAttribute("error", "Invalid OTP. Please try again.");
            return "verifyPage";
        }
    }

    private String generateRandomOtp() {
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }

    public String sendMail(String email, String otp) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);
        helper.setSubject("OTP Verification");
        helper.setText("Your OTP is " + otp);

        mailSender.send(message);
        return otp;
    }
}
