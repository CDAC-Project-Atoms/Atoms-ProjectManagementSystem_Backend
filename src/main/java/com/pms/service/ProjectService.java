package com.pms.service;

import java.util.List;

import com.pms.model.Chat;
import com.pms.model.Project;
import com.pms.model.User;

public interface ProjectService {
	
	public Project createProject(Project project, User user) throws Exception;

	public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception;
	
	public Project getProjectById(Long projectId) throws Exception;
	
	public void deleteProject(Long projectId, Long userId) throws Exception;
	
	public Project updateProject( Project updatedProject, Long id) throws Exception;
	
	public void addUserToProject(Long projectId, Long userId) throws Exception;
	
	public void removeUserFromProject(Long projectId, Long userId) throws Exception;
	
	public Chat getChatByProjectId(Long projectId) throws Exception;
	
}
