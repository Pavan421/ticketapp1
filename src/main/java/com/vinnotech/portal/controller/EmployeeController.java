package com.vinnotech.portal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	 * Getting the Employee based on EmpId
	 * 
	 * @param id
	 * @return
	 */
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
	@GetMapping
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
	 * Creating new Employee
	 * 
	 * @param emp
	 * @return
	 */
	@PostMapping
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
	 * updating Employee
	 * 
	 * @param emp
	 */
	@PutMapping
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
	 * Deleting Employee
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/id")
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
	 * getting all employees with desending order
	 * 
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
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
	 * getting all employees with Asending order
	 * 
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
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
	 * getting All old Employees detail desending order
	 * 
	 * @param isEmpDeleted
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
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
	 * getting All old Employees detail Asending order
	 * 
	 * @param isEmpDeleted
	 * @param offset
	 * @param pageSize
	 * @param field
	 * @return
	 */
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
}