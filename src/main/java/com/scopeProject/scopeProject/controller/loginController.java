
package com.scopeProject.scopeProject.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.scopeProject.scopeProject.model.LoginModel;
import com.scopeProject.scopeProject.model.registerModel;
import com.scopeProject.scopeProject.repository.registerRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class loginController {

    @Autowired
    private registerRepository registerrepository;   
    private CookController cookController;
    
    public loginController(CookController cookController) {
    	this.cookController=cookController;
    }

    @GetMapping("/login")
    public String showLoginPage(HttpServletRequest request, Model model) {
        // Extract user details from cookies
        String userEmail = cookController.getUserEmailFromCookie(request);
        String userPassword = cookController.getUserPasswordFromCookie(request);
        int userId = cookController.getIdFromCookie(request);

        // Check if the user is already logged in
        if (cookController.getUserIdFromCookie(request)) {
            // If user is logged in, but remember-me cookies are not set, redirect to login page
            if (userEmail == null && userPassword == null) {
                return "redirect:/dashbord";
            }
            // If user is logged in and remember-me cookies are set, redirect to dashboard
            return "login";
        } else {
            // If user is not logged in, show the login page
            return "login";
        }
    }

    	

    
    @PostMapping("/login")
    public String loginDetails(LoginModel login, @RequestParam(required = false) String rememberMe, Model model, 
            @RequestParam String email, HttpServletResponse response, HttpServletRequest request) {

        // Handling empty email or password
        if (email.isEmpty() || login.getPassword().isEmpty()) {
            model.addAttribute("error", "Email and password are required");
            return "login";
        }

        registerModel regval = registerrepository.findByEmail(email);

        if (regval != null) {
            if (login.getPassword().equals(regval.getPassword())) {
                int userId = regval.getId();
                int maxAgeInSeconds = 60 * 60 * 24 * 30 * 2; 
                // Create a cookie with the user ID
                Cookie userIdCookie = new Cookie("userId", String.valueOf(userId));
                userIdCookie.setMaxAge(maxAgeInSeconds);  // Set cookie expiration time in seconds (adjust as needed)
                userIdCookie.setPath("/");  // Set cookie path to "/" for the entire application
                response.addCookie(userIdCookie);
                
               

                // Remember Me functionality
                if ("on".equals(rememberMe)) {
                    // Store the user's email and password in cookies
                    Cookie emailCookie = new Cookie("email", email);
                    emailCookie.setMaxAge(maxAgeInSeconds);
                    emailCookie.setPath("/");

                    Cookie passwordCookie = new Cookie("password", login.getPassword());
                    passwordCookie.setMaxAge(maxAgeInSeconds);
                    passwordCookie.setPath("/");

                    response.addCookie(emailCookie);
                    response.addCookie(passwordCookie);
                }

                // Storing full name in cookie
                String userName = regval.getFullname();
                Cookie userNameCookie = new Cookie("userName", userName);
                userNameCookie.setMaxAge(maxAgeInSeconds);  
                userNameCookie.setPath("/"); 
                response.addCookie(userNameCookie);

                model.addAttribute("success", "Login successful");
                return "dashbord";  // Assuming your actual dashboard view is named "dashboard"
            } else {
                model.addAttribute("error", "Invalid email or password");
                return "login";
            }
        } else {
            model.addAttribute("error", "This email is not registered. Please register first");
            return "login";
        }
    }
}