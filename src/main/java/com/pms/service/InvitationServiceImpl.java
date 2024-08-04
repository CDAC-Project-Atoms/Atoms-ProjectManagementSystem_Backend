package com.pms.service;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

import com.pms.model.Invitation;

@Service
public class InvitationServiceImpl implements InvitationService{

	
	@Override
	public void sendInvitation(String email, Long projectId) throws MessagingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Invitation acceptInvitation(String token, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTokenByUserMail(String userEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteToken(String token) {
		// TODO Auto-generated method stub
		
	}

	
	
}
