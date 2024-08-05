package com.pms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.Issue;
import com.pms.model.Project;
import com.pms.model.User;
import com.pms.repository.IssueRepository;
import com.pms.request.IssueRequest;

@Service
public class IssueServiceImpl implements IssueService{

	@Autowired
	private IssueRepository issueRepository;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;
	
//	Method to get the Issue by issueId
	@Override
	public Issue getIssueById(Long issueId) throws Exception {
		Optional<Issue> issue = issueRepository.findById(issueId);
		if(issue.isPresent()) {
			return issue.get();
		}
		throw new Exception("No issues found with issueId " + issueId);
	}

//	Method to get all Issue of the project by projectId
	@Override
	public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
		
		return issueRepository.findByProjectId(projectId);
	}

//	Method to create the  issue and add in the DB
	@Override
	public Issue createIssue(IssueRequest issueRequest, User user) throws Exception {
		Project project = projectService.getProjectById(issueRequest.getProjectID());
		
		Issue issue = new Issue();
		issue.setTitle(issueRequest.getTitle());
		issue.setDescription(issueRequest.getDescription());
		issue.setStatus(issueRequest.getStatus());
		issue.setProjectID(issue.getProjectID());
		issue.setPriority(issueRequest.getPriority());
		issue.setDueDate(issueRequest.getDueDate());
		
		issue.setProject(project);
		
		return issueRepository.save(issue);
	}

//	Method to delete the issue 
	@Override
	public void deleteIssue(Long issueId, Long userId) throws Exception {
		getIssueById(issueId);
		issueRepository.deleteById(issueId);
	}

//	Method to add user to the issue
	@Override
	public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
		User user = userService.findUserById(userId);
		Issue issue = getIssueById(issueId);
		issue.setAssignee(user);
		
		return issueRepository.save(issue);
	}

//	Method to update the status of the issue
	@Override
	public Issue updateStatus(Long issueId, String status) throws Exception {
		
		Issue issue = getIssueById(issueId);
		issue.setStatus(status);
		
		return issueRepository.save(issue);
	}

}
