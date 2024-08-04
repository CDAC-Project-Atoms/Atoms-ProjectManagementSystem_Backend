package com.pms.service;

import javax.mail.MessagingException;

import com.pms.model.Invitation;

public interface InvitationService {

	public void sendInvitation (String email, Long projectId) throws MessagingException;
	
	public Invitation acceptInvitation(String token, Long userId ) throws Exception;
	
	public String getTokenByUserMail(String userEmail);
	
	public void deleteToken(String token);
		
}
