


package com.scopeProject.scopeProject.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scopeProject.scopeProject.model.registerModel;
import com.scopeProject.scopeProject.repository.registerRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class EditProfileController {
    @Autowired
    private registerRepository registerrepository;
    
    @Autowired
    private CookController cookController;

    public EditProfileController(CookController cookController) {
        this.cookController = cookController;
    }

    @GetMapping("/editprofile")
    public String showUpdateProfileForm( Model model, HttpServletRequest request) {
        if (cookController.getUserIdFromCookie(request)) {
        	 int id = cookController.getIdFromCookie(request);
            registerModel rm = registerrepository.findById(id);
            if (rm != null) {
                model.addAttribute("reg", rm);
                return "editprofile";
            } else {
            	
                model.addAttribute("error", "Profile with this ID not found");
                return "login";
            }
            // Return the view name without redirection
            
        } else {
            // Handle unauthorized access
            return "login"; // Redirect to login page or any other page
        }
    }
    

    @PostMapping("/editprofile")
    public String editProfileUpdate(@ModelAttribute("reg") registerModel reg, Model model,HttpServletRequest request) {
    	int id = cookController.getIdFromCookie(request);
    	registerModel rm = registerrepository.findById(id);
        
        if (cookController.getUserIdFromCookie(request)) {
        if (rm != null) {
            // Update fields based on reg object
            registerrepository.updateAllDetails(reg.getFullname(), reg.getDob(), reg.getEmail(),
                    reg.getMobileNumber(), reg.getAddress(), reg.getCountry(),
                    reg.getQualification(), reg.getZipCode(), reg.getCity(),
                    reg.getState(), reg.getGuardianName(), reg.getGuardianMobile());
            // Redirect to the updated profile page
           
        } else {
            // Handle the case when rm (profile with the given id) is not found
            model.addAttribute("error", "Profile with this ID not found");
            return "editprofile"; // Return the view name without the path
        }
        
        
    }
    return "editprofile";
}

}


