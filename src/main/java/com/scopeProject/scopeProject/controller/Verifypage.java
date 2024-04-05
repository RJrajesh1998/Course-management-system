package com.scopeProject.scopeProject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


//VerifypageController.java


@Controller
public class Verifypage {



 @GetMapping("/verifyPage")
 public String showVerifypage(Model model) {
     
     return "verifyPage";
 }
 

 


}
