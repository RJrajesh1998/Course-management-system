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

import com.scopeProject.scopeProject.model.forgotPassOtp;
import com.scopeProject.scopeProject.model.SignupModel;
import com.scopeProject.scopeProject.model.registerModel;
import com.scopeProject.scopeProject.repository.SignupRepository;
import com.scopeProject.scopeProject.repository.registerRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
@Controller
public class ForgotPasswordController {

    @Autowired
    private SignupRepository signupRepository;

    @Autowired
    private registerRepository registerRepository;

    @Autowired
    private JavaMailSender mailSender;

    
    private final CookController cookController ;
    
    public ForgotPasswordController (CookController cookController) {
    	this.cookController=cookController;
    }
    
    @GetMapping("/forgotPassOtp")
    public String showForgotPasswordPage(HttpServletRequest request) {
    	if(cookController.getUserIdFromCookie(request)) {
        return "forgotPassOtp";
    }
    	return "forgotPassOtp";
    }

    @PostMapping("/forgotPassOtp")
    public String sendOtp(@RequestParam String email, String otp,Boolean verified, forgotPassOtp editOtp, Model model) {
        try {
            registerModel forgot = registerRepository.findByEmail(email);
            SignupModel edit = signupRepository.findByEmail(email);
       

            if (forgot != null) {
                if (edit.getEmail().equals(editOtp.getEmail())) {
                	if(forgot.getPassword()!= null) {
                    String forgotOtp = generateRandomOtp();
                    editOtp.setOtp(forgotOtp);
                    // Update OTP in the database
                    
                    signupRepository.updateOtp(editOtp.getEmail(), forgotOtp);
                    
//                    signupRepository.updateVerified(email, false); 
                    sendMail(editOtp.getEmail(), forgotOtp);

                    model.addAttribute("email", editOtp.getEmail());
                    return "verifyPage";
                	}else {
                		model.addAttribute("error", "you not register in firsttime login");
                        return "forgotPassOtp";
                	}
                } else {
                    model.addAttribute("error", "Invalid email");
                    return "forgotPassOtp";
                }
            } else {
                model.addAttribute("error", "Email doesn't exist, please register first");
                return "redirect:/register";
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to send OTP");
            return "errorPage";
        }
    }

    @PostMapping("/verifyOtp")
    public String updateverifyOtp(@RequestParam String email, String otp, forgotPassOtp editOtp, Model model) {
        try {
            registerModel forgot = registerRepository.findByEmail(email);
            SignupModel edit = signupRepository.findByEmail(email);

            if (forgot != null && edit != null && edit.getEmail().equalsIgnoreCase(editOtp.getEmail())) {
                String forgotOtp = generateRandomOtp();

                // Update OTP only if the provided OTP matches the one in the database
                if (otp.equals(edit.getOtp())) {
//                	signupRepository.updateVerified(email, true); 
                    signupRepository.updateOtp(editOtp.getEmail(), forgotOtp);
                    editOtp.setOtp(forgotOtp);

                    sendMail(editOtp.getEmail(), forgotOtp);
                    model.addAttribute("email", editOtp.getEmail());
                    return "forgotpassword";
                } else {
                    model.addAttribute("error", "Incorrect OTP");
                    return "forgotPassOtp";
                }
            } else {
                model.addAttribute("error", "Invalid email");
                return "forgotPassOtp";
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to send OTP");
            return "errorPage";
        }
    }

    public String generateRandomOtp() {
        return String.valueOf(new Random().nextInt(900000) + 10000);
    }

    public String sendMail(String email, String otp) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);
        helper.setSubject("Forgot Password OTP Verification");
        helper.setText("Your OTP is " + otp);

        mailSender.send(message);
        return otp;
    }
    
    
    
//    @PostMapping("/forgotpassword")
//    public String forgotpasswordPage(@RequestParam String newpassword, String confirmpassword, String email, forgotpassword fpass, Model model) {
//        try {
//            registerModel user = registerRepository.findByEmail(email);
//            String em=fpass.getEmail();
//            if (user != null) {
//                if (newpassword.equals(confirmpassword)) {
//                    user.setPassword(newpassword);
//                    registerRepository.save(user);
//                    model.addAttribute("success", "Password generated successfully");
//                    return "login";
//                } else {
//                    model.addAttribute("error", "Password and confirm password do not match");
//                    return "forgotpassword";  // Updated this line
//                }
//            } else {
//                model.addAttribute("error", "User not found");
//                return "redirect:/register";
//            }
//        } catch (IncorrectResultSizeDataAccessException ex) {
//            // Log the exception for further analysis
//            // logger.error("IncorrectResultSizeDataAccessException: {}", ex.getMessage());
//
//            // Return a user-friendly error response
//            model.addAttribute("error", "Unexpected error occurred");
//            return "createpassword";
//        }
//    }

}