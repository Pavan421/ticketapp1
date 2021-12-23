package com.vinnotech.portal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinnotech.portal.model.Project;
import com.vinnotech.portal.service.ProjectService;

@CrossOrigin
@RestController
@RequestMapping("/api/project")
public class ProjectController {

	private static final String CLASSNAME = "ProjectController";
	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);
	@Autowired
	private ProjectService projectService;

	@PutMapping("/{empId}")
	public void addProject(@RequestBody Project project, @PathVariable Long empId) {

		String methodName = "addProject";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		projectService.createProjectWithEmp(project, empId);
	}

	@PutMapping("/asign/{empId}/{projectId}")
	public void addProject(@PathVariable Long empId, @PathVariable Long projectId) {
		String methodName = "@PutMapping addProject ";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");

		projectService.asignProject(empId, projectId);

	}

	@PutMapping
	public Project createProject(@RequestBody Project project) {

		String methodName = "@PutMapping createProject ";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		return projectService.createProject(project);
	}

	@GetMapping("/{projectId}")
	public Project createProject(@PathVariable Long projectId) {

		String methodName = "@GetMapping createProject ";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");

		return projectService.getProject(projectId);
	}

	@GetMapping
	public List<Project> createProject() {

		String methodName = "@GetMapping  List createProject ";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		return projectService.getAllProjects();
	}

	@DeleteMapping("/{projectId}")
	public void deleteProject(@PathVariable Long projectId) {

		String methodName = "deleteProject";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		projectService.deleteProject(projectId);
	}
}
