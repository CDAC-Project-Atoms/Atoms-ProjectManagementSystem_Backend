package com.pms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.Chat;
import com.pms.model.Project;
import com.pms.model.User;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	private UserService userService;
	
	@Override
	public Project createProject(Project project, User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project getProjectById(Long projectId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProject(Long projectId, Long userId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Project updateProject(Project updatedProject, Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUserToProject(Long projectId, Long userId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUserFromProject(Long projectId, Long userId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Chat getChatByProjectId(Long projectId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
