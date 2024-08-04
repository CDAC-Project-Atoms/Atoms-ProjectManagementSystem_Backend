package com.pms.service;

import javax.mail.MessagingException;

public interface EmailService {
	
	public void sendEmailWithToken(String userEmail, String link) throws MessagingException;
	
	

}
