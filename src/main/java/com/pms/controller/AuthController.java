package com.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.config.JwtProvider;
import com.pms.model.User;
import com.pms.repository.UserRepository;
import com.pms.request.LoginRequest;
import com.pms.response.AuthResponse;
import com.pms.service.CustomUserDetailsImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserDetailsImpl customUserDetailsImpl;
	
	// Create new user or Signup method
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
		
		User isUserExists = userRepository.findByEmail(user.getEmail());
		
		// Check if user already exists or not with same email
		if(isUserExists!=null) {
			throw new Exception("email already exist with another account");
		}
		
		// If user not exits then save the new user with email
		User createdUSer = new User();
		createdUSer.setPassword(passwordEncoder.encode(user.getPassword()));
		createdUSer.setEmail(user.getEmail());
		createdUSer.setFullName(user.getFullName());
		
		User savedUser = userRepository.save(createdUSer);
		
		// Authenticate the new user
		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		// Generate JWT token
		String jwt = JwtProvider.generateToken(authentication);	
		
		// Send the response with jwt token and success msg
		AuthResponse res = new AuthResponse();
		res.setMessage("signup success");
		res.setJwt(jwt);
		
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {
		
		String username=loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		// Authenticate the user
		Authentication authentication = authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	
		// Generate JWT token
		String jwt = JwtProvider.generateToken(authentication);	
				
		// Send the response with jwt token and success msg
		AuthResponse res = new AuthResponse();
		res.setMessage("signin success");
		res.setJwt(jwt);
				
		return new ResponseEntity<>(res, HttpStatus.CREATED);
		
	}

	// Method to valid username and password
	private Authentication authenticate(String username, String password) {
		
		UserDetails userDetails = customUserDetailsImpl.loadUserByUsername(username);
		
		// User Details not found / User does not exists
		if(userDetails==null) {
			throw new BadCredentialsException("invalid username");
		}
		
		// If user found then check the password
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("invalid passsword");
		}
		
		// If password is correct	
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		
	}
	
	
	
}
