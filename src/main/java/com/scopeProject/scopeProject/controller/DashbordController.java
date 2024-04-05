package com.scopeProject.scopeProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scopeProject.scopeProject.model.ChangePasswordModel;
import com.scopeProject.scopeProject.model.SignupModel;
import com.scopeProject.scopeProject.model.registerModel;
import com.scopeProject.scopeProject.repository.SignupRepository;
import com.scopeProject.scopeProject.repository.registerRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DashbordController {
	
	
//	 @GetMapping("/pickedcourse")
//	 public String showPickedCoursepage() {
//		return "pickedcourse";
//		 
//	 }
//
// @PostMapping("/pickedcourse")
// public String addPickedCourse() {
//	return "";
//	 
// }
 @GetMapping("/changepassword")
 public String showchangepassword(HttpServletRequest request) {
	 if(cookController.getUserIdFromCookie(request)) {
	return "changepassword";
	 }
		 
	 return "redirect:/login";
 }
 
 @Autowired
 private registerRepository registerrepository;
 private SignupRepository signuprepository;


 private final CookController cookController ;


 public DashbordController(CookController cookController) {
	 this.cookController=cookController;
 }
 
 
 
 @PostMapping("/changepassword")
 public String changepasswordPage(
                                  @RequestParam String newpassword,
                                  @RequestParam String existingpassword,
                                  @RequestParam String email,
                                  ChangePasswordModel cpm,
       
                                  Model model,HttpServletRequest request) {
	 
	 
     try {
         registerModel regval = registerrepository.findByEmail(email);
         try {
             
        	 
        	 if (cookController.getUserIdFromCookie(request)) {
        		 String userName = cookController.getUserNameFromCookie(request);
        		 String userEmail = cookController.getUserEmailFromCookie(request);
        		 
        		 if (regval != null) {
                     if (existingpassword.equals(regval.getPassword())) {
                         if (!existingpassword.equals(newpassword)) {
                        	 
                             regval.setPassword(newpassword);
                             registerrepository.save(regval); // Save the updated object to the database
                             model.addAttribute("success", "Password changed successfully");
                             return "login";
                         } else {
                             model.addAttribute("error", "New password should be different from the existing password");
                             return "changepassword";
                         }
                     } else {
                         model.addAttribute("error", "Existing password is incorrect");
                         return "error";
                     }
                
             } else {
                 model.addAttribute("error", "User not found");
                 return "redirect:/register";
             }
        		 
        	 }else {
        		 return "login";
        	 }
                
         } catch (IncorrectResultSizeDataAccessException ex) {
             model.addAttribute("error", "Unexpected error occurred");
             return "redirect:login";
         }
     } catch (NumberFormatException e) {
         e.printStackTrace();
         return "error"; // Handle the exception appropriately
     }
         
     
 }
}


