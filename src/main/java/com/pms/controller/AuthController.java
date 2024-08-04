package com.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.config.JwtProvider;
import com.pms.model.User;
import com.pms.repository.UserRepository;
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
	
	@PostMapping("/signup")
	public ResponseEntity<User> createUserHandler(@RequestBody User user) throws Exception {
		
		User isUserExists = userRepository.findByEmail(user.getEmail());
		
		if(isUserExists!=null) {
			throw new Exception("email already exist with another account");
		}
		
		User createdUSer = new User();
		createdUSer.setPassword(passwordEncoder.encode(user.getPassword()));
		createdUSer.setEmail(user.getEmail());
		createdUSer.setFullName(user.getFullName());
		
		User savedUser = userRepository.save(createdUSer);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = JwtProvider.generateToken(authentication);		
		
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}
	
	
	
	
}
