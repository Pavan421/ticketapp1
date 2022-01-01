package com.vinnotech.portal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinnotech.portal.model.HRPortalConstants;
import com.vinnotech.portal.model.Project;
import com.vinnotech.portal.service.ProjectService;

@CrossOrigin
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	private static final String CLASSNAME = "ProjectController";
	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	private ProjectService projectService;

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@PostMapping("/create")
	public ResponseEntity<String> createProject(@RequestBody Project project) {
		String methodName = "createProject";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		String createdMsg = projectService.createProject(project);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "creating Project");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(createdMsg);
	}

	/*
	 * @PutMapping("/{empId}") public void addProject(@RequestBody Project
	 * project, @PathVariable("empId") Long empId) { String methodName =
	 * "addProject"; LOGGER.info(CLASSNAME + ": Entering into the " + methodName +
	 * " method"); projectService.createProjectWithEmp(project, empId); }
	 */

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@PutMapping("/assign/{empId}/{projectId}")
	public ResponseEntity<String> assignProject(@PathVariable("empId") Long empId,
			@PathVariable("projectId") Long projectId) {
		String methodName = "assignProject";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		String assignedProject = projectService.assignProject(empId, projectId);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Assigning Project");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(assignedProject);
	}

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@GetMapping("/{projectId}")
	public ResponseEntity<Project> getProject(@PathVariable("projectId") Long projectId) {
		String methodName = "getProject";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Project project = projectService.getProject(projectId);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Getting Project");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(project);
	}

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@GetMapping
	public ResponseEntity<List<Project>> getAllProjects() {
		String methodName = "getAllProjects";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		List<Project> projects = projectService.getAllProjects();
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Getting Projects");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(projects);
	}

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@DeleteMapping("/{projectId}")
	public ResponseEntity<String> deleteProject(@PathVariable("projectId") Long projectId) {
		String methodName = "deleteProject";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		String DeletedProject = projectService.deleteProject(projectId);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Deleting Projects");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(DeletedProject);
	}
}
