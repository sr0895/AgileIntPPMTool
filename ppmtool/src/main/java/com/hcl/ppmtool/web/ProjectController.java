package com.hcl.ppmtool.web;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.hcl.ppmtool.domain.Project;
import com.hcl.ppmtool.services.MapValidationErrorService;
import com.hcl.ppmtool.services.ProjectService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
@RestController
@RequestMapping("api/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if(errorMap != null)
			return errorMap;
		Project project1 = projectService.saveOrUpdate(project);
		return new ResponseEntity<Project>(project,HttpStatus.CREATED);
	}
	
	
}
