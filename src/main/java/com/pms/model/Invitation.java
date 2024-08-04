package com.pms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invitation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
		
	private String token;
	private String email;
	private Long projectId;
	
		
}
