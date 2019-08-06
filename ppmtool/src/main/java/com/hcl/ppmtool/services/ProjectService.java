package com.hcl.ppmtool.services;
import org.springframework.stereotype.Service;

import com.hcl.ppmtool.domain.Project;
import com.hcl.ppmtool.repositories.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository; 
	public Project saveOrUpdate(Project project) {
		return projectRepository.save(project);
	}
	
}
