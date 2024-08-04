package com.pms.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Issue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String title;   	// title of Issue
	private String description;	// description of Issue
	private String status;		// status of Issue - Complete, Inprogress, start
	private Long projectID;		// projectID of Issue
	private String priority;	// priority of Issue - priority or not
	private LocalDate dueDate;	// dueDate of Issue
	
	private List<String> tags = new ArrayList<>();
	
	@ManyToOne
	private User assignee;
	
	@JsonIgnore
	@ManyToOne
	private Project project;   // Many issues of single project
	
	@JsonIgnore
	@OneToMany(mappedBy = "issue", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();
	
	
	
	
	

}
