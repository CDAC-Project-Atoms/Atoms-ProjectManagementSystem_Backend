package com.pms.service;



import com.pms.model.User;


public interface UserService {
	
	public User findUserProfileByJwt(String jwt) throws Exception;
	
	public User findUserByEmail(String email) throws Exception;
	
	public User findUserById(Long userId) throws Exception;
	
	public User updateUserProjectSize(User user, int number);
	

}
