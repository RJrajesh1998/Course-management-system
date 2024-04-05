package com.scopeProject.scopeProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.scopeProject.scopeProject.repository.contactRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service


public class contactService {

//	private JavaMailSender mailSender;

//public void sendMail( String name,String course,String gmail,String phone) throws MessagingException  {
//	
//	try {MimeMessage message=mailSender.createMimeMessage();
//	MimeMessageHelper helper=new MimeMessageHelper(message);
//	helper.setTo("rajeshrajakumar1998@gmail.com");
//	helper.setSubject("mail verification");
//	boolean html=true;
//
//		helper.setText("<b>name </b>"+ name+"<br>"
//				+"<b>course </b>" +course +"<br>"
//				+"<b>gmail </b>" +gmail +"<br>"
//				+"<b>phone </b>" + phone
//				);
//		mailSender.send(message);
//	} catch (MessagingException e) {
//		
//		e.printStackTrace();
//	}
	
		
	
//}

}
