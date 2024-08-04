package com.pms.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String description;
	private String category;
	
	private List<String> tags = new ArrayList<>();
	
	@JsonIgnore
	@OneToOne(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	private Chat chat;     // Each project has one chat 
	
	@ManyToOne
	private User owner;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Issue> issues = new ArrayList<>();  // One project has multiple issues
	
	@ManyToMany
	private List<User> team = new ArrayList<>();  // multiple user work on multiple project
	
	
}
