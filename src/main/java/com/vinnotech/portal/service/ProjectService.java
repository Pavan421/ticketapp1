package com.vinnotech.portal.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.exception.ValidateException;
import com.vinnotech.portal.model.Employee;
import com.vinnotech.portal.model.Project;
import com.vinnotech.portal.repository.EmployeeRepository;
import com.vinnotech.portal.repository.ProjectRepository;

@Service
public class ProjectService {

	private static final String CLASSNAME = "ProjectService";
	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	public void createProjectWithEmp(Project project, Long empId) {

		String methodName = "createProjectWithEmp";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);

		Employee emp = employeeRepository.findById(empId).get();
		emp.getProjects().add(project);
		employeeRepository.save(emp);
	}

	public void asignProject(Long empId, Long projectId) {

		String methodName = "asignProject";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);

		Employee emp = employeeRepository.findById(empId).get();
		Project project = projectRepository.findById(projectId).get();
		emp.getProjects().add(project);
		employeeRepository.save(emp);
	}

	public Project createProject(Project project) {

		String methodName = "createProject";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		return projectRepository.save(project);
	}

	public Project getProject(Long ProjectId) {

		String methodName = "getProject";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		Optional<Project> project = projectRepository.findById(ProjectId);
		if (project.isPresent()) {
			return project.get();
		}
		throw new ValidateException("Record not Found");
	}

	public List<Project> getAllProjects() {

		String methodName = "getAllProjects";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		return projectRepository.findAll();
	}

	public void deleteProject(Long projectId) {

		String methodName = "deleteProject";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);

		projectRepository.deleteById(projectId);
	}
}
