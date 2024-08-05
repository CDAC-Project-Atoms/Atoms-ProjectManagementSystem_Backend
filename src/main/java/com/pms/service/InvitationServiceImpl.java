package com.pms.service;

import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.Invitation;
import com.pms.repository.InvitationRepository;

@Service
public class InvitationServiceImpl implements InvitationService{

	@Autowired
	private InvitationRepository invitationRepository;
	
	@Autowired
	private EmailService emailService;
	
	// Method to send the invitation
	@Override
	public void sendInvitation(String email, Long projectId) throws MessagingException {

		String invitationToken = UUID.randomUUID().toString();
		
		Invitation invitation = new Invitation();
		
		invitation.setEmail(email);
		invitation.setProjectId(projectId);
		invitation.setToken(invitationToken);
		
		invitationRepository.save(invitation);	
		
		// create the invitation link with unique invitation token
		String invitationLink = "http://localhost:8282/accept_invitation?token="+invitationToken;
		
		// send the email to the user email with invitation link in it
		emailService.sendEmailWithToken(email, invitationLink);
		
	}

	// Method when user accept the invitation link
	@Override
	public Invitation acceptInvitation(String token, Long userId) throws Exception {

		// Match the invitation with the invitation token
		Invitation invitation = invitationRepository.findByToken(token);
		if(invitation==null) {
			throw new Exception("invalid invitation token");
		}
		
		return invitation;
	}

	// Method to get the token from user mail
	@Override
	public String getTokenByUserMail(String userEmail) {
		
		Invitation invitation = invitationRepository.findByEmail(userEmail);
				
		return invitation.getToken();
	}

	// After user accept the invitation, delete the invitation along the invitation token
	@Override
	public void deleteToken(String token) {

		Invitation invitation = invitationRepository.findByToken(token);
		
		invitationRepository.delete(invitation);
		
	}

	
	
}
