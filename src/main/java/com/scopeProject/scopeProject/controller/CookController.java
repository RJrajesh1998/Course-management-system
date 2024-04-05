package com.scopeProject.scopeProject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CookController {

    @GetMapping("/")
    public String somePage(Model model, HttpServletRequest request) {

        return "/";  
    }

    // Example method to retrieve user ID from the cookie
    public boolean getUserIdFromCookie(HttpServletRequest request) {
    	try {
    	
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    int userId = Integer.parseInt(cookie.getValue());
                    // Use userId as needed (e.g., fetch user details from the database)
//                    model.addAttribute("userId", userId);
                   return true; 
                }
            }
        }
        return false;
    	}catch(Exception e){
    		return false ;
    	}
    }
    
    public int getIdFromCookie(HttpServletRequest request) {
    	try {
    	
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    int userId = Integer.parseInt(cookie.getValue());
                   return userId; 
                }
            }
        }
        return 0;
    	}catch(Exception e){
    		return 0 ;
    	}
    }
    
    
    public String getUserNameFromCookie(HttpServletRequest request) {
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("userName".equals(cookie.getName())) {
                        return cookie.getValue();
                    }
                }
            }
            return null; // Return null if the "userName" cookie is not found
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
}
    
    public String getUserEmailFromCookie(HttpServletRequest request) {
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("userEmail".equals(cookie.getName())) {
                        return cookie.getValue();
                    }
                }
            }
            return null; // Return null if the "userName" cookie is not found
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public String getUserPasswordFromCookie(HttpServletRequest request) {
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("userPassword".equals(cookie.getName())) {
                        return cookie.getValue();
                    }
                }
            }
            return null; // Return null if the "userName" cookie is not found
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

