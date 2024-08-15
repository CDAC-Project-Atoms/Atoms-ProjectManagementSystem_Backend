package com.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
	PasswordResetToken findByToken(String token);
}
