package com.scopeProject.scopeProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.scopeProject.scopeProject.model.registerModel;
import com.scopeProject.scopeProject.repository.registerRepository;



@Controller
//@RequestMapping("/register") 
public class registerController {
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
    	registerModel register=new registerModel();
    	model.addAttribute("reg",register);
    	return "register";
    }
    
    @Autowired registerRepository registerrepository;
    
    @PostMapping("register")
    public String addDataRegister(@ModelAttribute("reg")registerModel register,Model model, String email) {
    	registerModel registerrep=registerrepository.findByEmail(email);
    	
    	if(registerrep == null )
    	{
    		registerrepository.save(register);
    		model.addAttribute("success", "successfull");
        	return "regsuccess";	
    	}else {
    		model.addAttribute("error","you are already registerd go to login page");
    		
    	}
		return "register";
	

}
}