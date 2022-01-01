package com.vinnotech.portal.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.exception.HRPortalException;
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

	/*
	 * public void createProjectWithEmp(Project project, Long empId) { String
	 * methodName = "createProjectWithEmp"; LOGGER.info(CLASSNAME +
	 * ": Entering into the " + methodName); Employee emp =
	 * employeeRepository.findById(empId).get(); emp.getProjects().add(project);
	 * employeeRepository.save(emp); }
	 */
	public String createProject(Project project) {
		String methodName = "createProject";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			projectRepository.save(project);
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return "created Project sucsessfully";
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + ": got error while creating Project " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	public String assignProject(Long empId, Long projectId) {
		String methodName = "assignProject";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Employee emp = employeeRepository.findById(empId).get();
			Project project = projectRepository.findById(projectId).get();
			emp.getProjects().add(project);
			employeeRepository.save(emp);
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return "Assigned Project sucsessfully";
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + ": got error while Assigning Project " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	public Project getProject(Long ProjectId) {
		String methodName = "getProject";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Project project = projectRepository.findById(ProjectId).get();
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return project;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + ": got error while getting Project " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	public List<Project> getAllProjects() {
		String methodName = "getAllProjects";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			List<Project> projects = projectRepository.findAll();
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return projects;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + ": got error while getting Projects " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	public String deleteProject(Long projectId) {
		String methodName = "deleteProject";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			projectRepository.deleteById(projectId);
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return "Deleted Project sucsessfully";
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + ": got error while Deleting Project " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		}
	}
}
