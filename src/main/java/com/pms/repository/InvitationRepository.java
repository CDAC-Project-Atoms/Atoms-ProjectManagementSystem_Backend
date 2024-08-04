package com.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.Invitation;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
	
	public Invitation findByToken(String token);
	
	public Invitation findByEmail(String userEmail);
	
}
