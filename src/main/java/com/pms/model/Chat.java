package com.pms.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@OneToOne
	private Project project; // Each chat has one project
	
	@JsonIgnore
	@OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Message> messages = new ArrayList<>();
	
	@ManyToMany
	private List<User> users = new ArrayList<>();
	
	
	
	
}
