package com.scopeProject.scopeProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {
	@Autowired
	private CookController cookController;
	

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
	    // Clearing cookies
	    
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            cookie.setMaxAge(0); // Delete the cookie by setting its max age to 0
	            cookie.setPath("/"); // Set cookie path to "/" for the entire application
	            response.addCookie(cookie);
	        }
	    }

	    // Redirecting to the login page after logout
	    return "redirect:/login";
	}
}


