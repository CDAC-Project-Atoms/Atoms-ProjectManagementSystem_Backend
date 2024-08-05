package com.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{

	public Subscription findByUserId(Long userId);
		
}
