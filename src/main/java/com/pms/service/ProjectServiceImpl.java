package com.pms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.Chat;
import com.pms.model.Project;
import com.pms.model.User;
import com.pms.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private ChatService chatService;
	
	@Override
	public Project createProject(Project project, User user) throws Exception {
		// Created new project
		Project createdProject = new Project();
		
		createdProject.setOwner(user);
		createdProject.setTags(project.getTags());
		createdProject.setName(project.getName());
		createdProject.setCategory(project.getCategory());
		createdProject.setDescription(project.getDescription());
		createdProject.getTeam().add(user);
		
		// Save the project in DB
		Project savedProject = projectRepository.save(createdProject);
		
		// Create the chat for the project
		Chat chat = new Chat();
		chat.setProject(savedProject);
		
		Chat projectChat = chatService.createChat(chat);
		savedProject.setChat(projectChat);
		
		return savedProject;
	}
 
	@Override
	public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {
		List<Project> projects = projectRepository.findByTeamContainingOrOwner(user, user);
		
		if(category!=null) { // filter out the projects based on the categories
			projects = projects.stream().filter(project -> project.getCategory().equals(category))
						.collect(Collectors.toList());
		}
		
		if(tag!=null) { // filter out the projects based on the tags
			projects = projects.stream().filter(project -> project.getTags().contains(tag))
						.collect(Collectors.toList());
		}
		
		return projects;
	}

	@Override
	public Project getProjectById(Long projectId) throws Exception {
		
		Optional<Project> optionalProject = projectRepository.findById(projectId);
		
		if(optionalProject.isEmpty()) { // get the project by projectId
			throw new Exception("project not found");
		}
		
		return optionalProject.get();
	}

	@Override
	public void deleteProject(Long projectId, Long userId) throws Exception {
		
		getProjectById(projectId);
		
		projectRepository.deleteById(userId);
		
	}

	@Override
	public Project updateProject(Project updatedProject, Long id) throws Exception {
		
		// get project by id
		Project project = getProjectById(id);
		
		// update existing project with updatedProject fields
		project.setName(updatedProject.getName());
		project.setDescription(updatedProject.getDescription());
		project.setTags(updatedProject.getTags());
		
		return projectRepository.save(project);
	}

	@Override
	public void addUserToProject(Long projectId, Long userId) throws Exception {
		
		Project project = getProjectById(projectId);  // get project to add user in it
		User user = userService.findUserById(userId); // get the user by id to add in project
		
		if(!project.getTeam().contains(user)) { 	// check if user already presents in project
			project.getChat().getUsers().add(user);
			project.getTeam().add(user); 			// add user in project
		}
		
		projectRepository.save(project);
		
	}

	@Override
	public void removeUserFromProject(Long projectId, Long userId) throws Exception {
		
		Project project = getProjectById(projectId);  	// get project to remove user in it
		User user = userService.findUserById(userId); 	// get the user by id to remove in project
		
		if(project.getTeam().contains(user)) { 	
			project.getChat().getUsers().remove(user);
			project.getTeam().remove(user); 			// remove user in project
		}
		
		projectRepository.save(project);
		
	}

	@Override
	public Chat getChatByProjectId(Long projectId) throws Exception {
		Project project = getProjectById(projectId);
				
		return project.getChat();
	}

	@Override
	public List<Project> searchProjects(String keyword, User user) throws Exception {
		
		List<Project> projects = projectRepository.findByNameContainingAndTeamContains(keyword, user);
				
		return projects;
	}

}
