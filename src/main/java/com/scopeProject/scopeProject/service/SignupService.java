package com.scopeProject.scopeProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.scopeProject.scopeProject.model.SignupModel;
import com.scopeProject.scopeProject.repository.SignupRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class SignupService {
   @Autowired
	
	private SignupRepository signupSRepository;
	
	public String findByEmail(SignupModel ftlogin) {
		return findByEmail(ftlogin);
		
	}
	
	
	@Autowired
	private JavaMailSender mailSender;
	
 
}
