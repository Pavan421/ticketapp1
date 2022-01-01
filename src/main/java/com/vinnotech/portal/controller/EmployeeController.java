package com.vinnotech.portal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.vinnotech.portal.model.Employee;
import com.vinnotech.portal.model.HRPortalConstants;
import com.vinnotech.portal.service.EmployeeService;

@CrossOrigin
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private static final String CLASSNAME = "EmployeeController";
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService empService;

	/**
	 * Creating new Employee
	 * 
	 * @param emp
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@PostMapping("/create")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee emp) {
		String methodName = "saveEmployee";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Employee createdemp = empService.saveEmployee(emp);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Creating New Employee");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(createdemp);
	}

	/**
	 * Getting the Employee based on EmpId
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_RECRUITER_EMPLOYEE_ONLY)
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
		String methodName = "getEmployee";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Employee emp = empService.getEmployee(id);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting the Employee");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(emp);
	}

	/**
	 * Getting the all Employees Without pagination and Sorting
	 * 
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@GetMapping()
	public ResponseEntity<List<Employee>> getAllEmployees() {
		String methodName = "getAllEmployees";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		List<Employee> lEmployees = empService.getAllEmployees();
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting the All Employees");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(lEmployees);
	}

	/**
	 * updating Employee
	 * 
	 * @param emp
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@PutMapping("/update")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee emp) {
		String methodName = "updateEmployee";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		empService.saveEmployee(emp);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "updating New Employee");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body("Employee updated successfully");
	}

	/**
	 * Getting all employees with descending order
	 * 
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@GetMapping("/sortandpagedesc/{offset}/{pageSize}/{field}")
	private ResponseEntity<Page<Employee>> getAllEmployeeswithSortAndPagiDesc(@PathVariable int offset,
			@PathVariable int pageSize, @PathVariable String field) {
		String methodName = "getAllEmployeeswithSortAndPagiDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Page<Employee> pageEmpsDesc = empService.getAllEmployeeswithSortAndPagiDesc(offset, pageSize, field);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting all Employees with desending order");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(pageEmpsDesc);
	}

	/**
	 * Getting all employees with Ascending order
	 * 
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@GetMapping("/sortandpageasc/{offset}/{pageSize}/{field}")
	private ResponseEntity<Page<Employee>> getAllEmployeeswithSortAndPagiASC(@PathVariable int offset,
			@PathVariable int pageSize, @PathVariable String field) {
		String methodName = "getAllEmployeeswithSortAndPagiASC";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Page<Employee> pageEmpAse = empService.getAllEmployeeswithSortAndPagiASC(offset, pageSize, field);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting all Employees Asending order");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(pageEmpAse);
	}

	/**
	 * Getting All old Employees detail descending order
	 * 
	 * @param isEmpDeleted
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@GetMapping("/spEmpDeldesc/{isEmpDeleted}/{offset}/{pageSize}/{field}")
	private ResponseEntity<Page<Employee>> getAllDelEmpswithSortAndPagiDesc(@PathVariable boolean isEmpDeleted,
			@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
		String methodName = "getAllJobswithSortAndPagiASC";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Page<Employee> delEmpsDesc = empService.getAllEmployeeswithSortAndPagiDelDesc(isEmpDeleted, offset, pageSize,
				field);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting all   Employees based on deleted desending  order");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(delEmpsDesc);
	}

	/**
	 * Getting All old Employees detail Ascending order
	 * 
	 * @param isEmpDeleted
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@GetMapping("/spEmpDelasc/{isEmpDeleted}/{offset}/{pageSize}/{field}")
	private ResponseEntity<Page<Employee>> getAllDelEmpswithSortAndPagiAsc(@PathVariable boolean isEmpDeleted,
			@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
		String methodName = "getAllJobswithSortAndPagiASC";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Page<Employee> delEmpsAsc = empService.getAllEmployeeswithSortAndPagiDelASC(isEmpDeleted, offset, pageSize,
				field);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting all   Employees based on deleted desending  order");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(delEmpsAsc);
	}

	/**
	 * Deleting Employee
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id) {
		String methodName = "deleteEmployee";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		String empDeleted = empService.deleteEmployee(id);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "deleting New Employee");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(empDeleted);
	}

	/**
	 * Getting Employee by Search 
	 * @param serachParam
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@GetMapping("/searchEmpsByParam/{serachParam}/{offset}/{pageSize}")
	public ResponseEntity<Page<Employee>> getEmpsBySearchTerm(@PathVariable("serachParam") String serachParam,
			@PathVariable("offset") int offset, @PathVariable("pageSize") int pageSize) {
		String methodName = "getEmpsBySearchTerm";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		Page<Employee> searchEmpsList = empService.searchEmpsByParam(serachParam, offset, pageSize);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting All Courses by search term");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(searchEmpsList);
	}
}