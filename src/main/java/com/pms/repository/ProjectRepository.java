package com.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.pms.model.Project;
import com.pms.model.User;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	// Project created By Owner
//	List<Project> findByOwner(User user);
	
	// Method use to search project By name
	List<Project> findByNameContainingAndTeamContains(String partialName, User user);
	
	// Method to Find project by Team
//	@Query("SELECT p from Project p join p.team t where t=:user")
//	List<Project> findProjectByTeam(@Param("user") User user);

	
	List<Project> findByTeamContainingOrOwner(User user, User owner);
	
	
}
