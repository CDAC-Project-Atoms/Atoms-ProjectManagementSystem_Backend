package com.pms.service;

import java.util.List;
import java.util.Optional;

import com.pms.model.Issue;
import com.pms.model.User;
import com.pms.request.IssueRequest;

public interface IssueService {
	
	public Issue getIssueById(Long issueId) throws Exception;
	
	public List<Issue> getIssueByProjectId(Long projectId) throws Exception;
	
	public Issue createIssue(IssueRequest issue, User user) throws Exception;
	
	public void deleteIssue(Long issueId, Long userId) throws Exception;
	
	public Issue addUserToIssue(Long issueId, Long userId) throws Exception;
	
	public Issue updateStatus(Long issueId, String status) throws Exception;

}
