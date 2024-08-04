package com.pms.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendEmailWithToken(String userEmail, String link) throws MessagingException {
		
		MimeMessage mimeMessages = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessages, "utf-8");
		
		String subject = "Join Project Team Invitation";
		String text = "Click the link to join the project team: " +link;
		
		helper.setSubject(subject);
		helper.setText(text, true);
		helper.setTo(userEmail);
		
		try {
			javaMailSender.send(mimeMessages);
		} 
		catch(MailSendException e) {
			
			throw new MailSendException("Failed to send email");						
		}
		
		
		
	}

}
