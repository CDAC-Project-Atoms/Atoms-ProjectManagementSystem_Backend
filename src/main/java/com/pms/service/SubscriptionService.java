package com.pms.service;

import com.pms.model.PlanType;
import com.pms.model.Subscription;
import com.pms.model.User;

public interface SubscriptionService {
	
	public Subscription createSubscription(User user);
	
	public Subscription getUsersSubscription(Long userId) throws Exception;
	
	public Subscription upgradeSubscription(Long userId, PlanType planType);
	
	public boolean isValid(Subscription subscription);

}
