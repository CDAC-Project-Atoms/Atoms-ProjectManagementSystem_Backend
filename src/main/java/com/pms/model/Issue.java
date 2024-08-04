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
	
	
	@JsonIgnore				// whenever we fetch issue from frontend then don't fetch JsonIgnore field
	@OneToMany(mappedBy = "issue", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Long getProjectID() {
		return projectID;
	}


	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}


	public String getPriority() {
		return priority;
	}


	public void setPriority(String priority) {
		this.priority = priority;
	}


	public LocalDate getDueDate() {
		return dueDate;
	}


	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}


	public List<String> getTags() {
		return tags;
	}


	public void setTags(List<String> tags) {
		this.tags = tags;
	}


	public User getAssignee() {
		return assignee;
	}


	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}


	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}


	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	

}
