package com.pms.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.PlanType;
import com.pms.model.Subscription;
import com.pms.model.User;
import com.pms.repository.SubscriptionRepository;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private UserService userService;
	
//	Method to create the subscription for the user
	@Override
	public Subscription createSubscription(User user) {
		Subscription subscription = new Subscription();
		subscription.setUser(user);
		subscription.setSubscriptionStartDate(LocalDate.now());
		subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
		subscription.setValid(true);
//		Initially Plan type of new user will be free
		subscription.setPlanType(PlanType.FREE); 
		
		return subscriptionRepository.save(subscription);
	}

//	Method to get the subscription of user when user wants to check the current plan type
	@Override
	public Subscription getUsersSubscription(Long userId) throws Exception {
		
		Subscription subscription = subscriptionRepository.findByUserId(userId);
		if(!isValid(subscription)) {
			subscription.setPlanType(PlanType.FREE);
			subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
			subscription.setSubscriptionStartDate(LocalDate.now());
		}
		
		return subscriptionRepository.save(subscription);
	} 

//	Method to upgrade the plan type of user
	@Override
	public Subscription upgradeSubscription(Long userId, PlanType planType) {
		Subscription subscription = subscriptionRepository.findByUserId(userId);
//		Get the current plan type
		subscription.setPlanType(planType);
		subscription.setSubscriptionStartDate(LocalDate.now());
		if(planType.equals(planType.ANNUALLY)) {
			subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
		}
		else {
			subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(1));
		}
		return subscriptionRepository.save(subscription);
	} 

//	Check if the subscription is valid or end/invalid
	@Override
	public boolean isValid(Subscription subscription) {
		if(subscription.getPlanType().equals(PlanType.FREE)) {
			return true;
		}
		LocalDate endDate = subscription.getSubscriptionEndDate();
		LocalDate currentDate = LocalDate.now();
		
		return endDate.isAfter(currentDate) || endDate.isEqual(currentDate);
	}

}
