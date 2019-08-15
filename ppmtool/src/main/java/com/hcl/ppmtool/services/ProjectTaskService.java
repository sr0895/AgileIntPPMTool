package com.hcl.ppmtool.services;
import org.springframework.stereotype.Service;

import com.hcl.ppmtool.domain.Backlog;
import com.hcl.ppmtool.domain.Project;
import com.hcl.ppmtool.domain.ProjectTask;
import com.hcl.ppmtool.exceptions.ProjectNotFoundException;
import com.hcl.ppmtool.repositories.BacklogRepository;
import com.hcl.ppmtool.repositories.ProjectRepository;
import com.hcl.ppmtool.repositories.ProjectTaskRepository;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
@Service
public class ProjectTaskService {
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	@Autowired
    private ProjectRepository projectRepository;

	
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
		 try {
	            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
	            projectTask.setBacklog(backlog);
	            Integer BacklogSequence = backlog.getPTSequence();
	            BacklogSequence++;
	            backlog.setPTSequence(BacklogSequence);
	            projectTask.setProjectSequence(backlog.getProjectIdentifier()+"-"+BacklogSequence);
	            projectTask.setProjectIdentifier(projectIdentifier);
	            if(projectTask.getStatus()==""|| projectTask.getStatus()==null){
	                projectTask.setStatus("TO_DO");
	            }
	            if(projectTask.getPriority()==null){
	                projectTask.setPriority(3);
	            }
	            return projectTaskRepository.save(projectTask);
	        }catch (Exception e){
	            throw new ProjectNotFoundException("Project not Found");
	        }
	}
	 public Iterable<ProjectTask>findBacklogById(String id){
	        Project project = projectRepository.findByProjectIdentifier(id);
	        if(project==null){
	            throw new ProjectNotFoundException("Project with ID: '"+id+"' does not exist");
	        }
	        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
	    }
	
	public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id){
        return projectTaskRepository.findByProjectSequence(pt_id);
    }
}
