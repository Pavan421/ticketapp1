package com.vinnotech.portal.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vinnotech.portal.config.JwtUtil;
import com.vinnotech.portal.exception.HRPortalException;
import com.vinnotech.portal.model.Employee;
import com.vinnotech.portal.model.HRPortalConstants;
import com.vinnotech.portal.model.UserReg;
import com.vinnotech.portal.repository.EmployeeRepository;
import com.vinnotech.portal.repository.UserRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final String CLASSNAME = "EmployeeServiceImpl";
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository empRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	/**
	 * Create new employee
	 */
	@Override
	public Employee saveEmployee(Employee emp) {
		String methodName = "saveEmployee";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			/*
			 * HttpServletRequest request = ((ServletRequestAttributes)
			 * RequestContextHolder.getRequestAttributes()) .getRequest(); String jwtToken =
			 * jwtUtil.extractJwtFromRequest(request);
			 */
			Employee remp = empRepository.save(emp);
			long empId = remp.getId();
			if (!StringUtils.isEmpty(empId)) {
				UserReg userReg = new UserReg();
				userReg.setEmpId(empId);
				userReg.setUsername(emp.getShortName());
				userReg.setPassword(bcryptEncoder.encode(emp.getShortName() + "@1234"));
				userReg.setRole(emp.getAccRole());
				userReg.setStatus(true);
				if (HRPortalConstants.ROLE_ADMIN.equals(emp.getAccRole())) {
					userReg.setPriority(4);
				} else if (HRPortalConstants.ROLE_HR.equals(emp.getAccRole())) {
					userReg.setPriority(3);
				} else if (HRPortalConstants.ROLE_RECRUITER.equals(emp.getAccRole())) {
					userReg.setPriority(2);
				} else {
					userReg.setPriority(1);
				}
				UserReg u1 = userRepository.save(userReg);
				if (!StringUtils.isEmpty(u1.getUsername())) {
					String html = "<!doctype html>\n" + "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\"\n"
							+ "      xmlns:th=\"http://www.thymeleaf.org\">\n" + "<head>\n"
							+ "    <meta charset=\"UTF-8\">\n" + "    <meta name=\"viewport\"\n"
							+ "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n"
							+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n"
							+ "    <title>Email</title>\n" + "</head>\n" + "<body>\n" + "<div>Hi  <b>"
							+ emp.getFirstName() + "</b></div>\n" + "<div><b><h4>Congratulation!!!</h4></b></div>\n"
							+ "<div>username <b>" + u1.getUsername() + "</b></div>\n" + "\n"
							+ "<div> Your password is <b>" + emp.getShortName() + "@1234" + "</b></div>\n"
							+ "<div>  <b> regards</b></div>\n" + "<div> <b> admin</b></div>\n" + "</body>\n"
							+ "</html>\n";
					emailService.sendMail(emp.getOfficialEmailId(), "cloudcare", html);
				}
			}
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return remp;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting Employee " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	/**
	 * Get the Employee details based on employee Id
	 */
	@Override
	public Employee getEmployee(Long empId) {
		String methodName = "getEmployee";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Optional<Employee> employee = empRepository.findById(empId);
			Employee emp = employee.get();
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return emp;

		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting Employee " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
			// throw new ValidateException("No records found");
		}
		/*
		 * LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method"); throw
		 * new ValidateException("Record not found with id : " + empId);
		 */
	}

	/**
	 * getting the All Employees with out sorting
	 */
	@Override
	public List<Employee> getAllEmployees() {
		String methodName = "getAllEmployees";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			List<Employee> employees = empRepository.findAll();
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return employees.stream().filter(e -> e.isEmployeeDeleted() != true).collect(Collectors.toList());
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting Employee " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	/**
	 * deleting Employee based On id
	 */
	@Override
	public String deleteEmployee(Long empId) {
		String methodName = "deleteEmployee";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Optional<Employee> optEmployee = empRepository.findById(empId);
			Employee emp = new Employee();
			if (optEmployee.isPresent() && !optEmployee.get().isEmployeeDeleted()) {
				emp = optEmployee.get();
				emp.setEmployeeDeleted(Boolean.TRUE);
				empRepository.save(emp);
			}
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while deleting  Employee " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return "Employee Deleted Successfully";
	}

	/**
	 * getting All Employees without pagination and pagination with descending order
	 */
	@Override
	public Page<Employee> getAllEmployeeswithSortAndPagiDesc(int offset, int pageSize, String field) {
		String methodName = "getAllEmployeeswithSortAndPagiDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Page<Employee> employees = empRepository
					.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, field)));
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return employees;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting Employee without pagination and pagination desc "
					+ methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	/**
	 * getting all Employees ascending order with pagination and sorting
	 */
	@Override
	public Page<Employee> getAllEmployeeswithSortAndPagiASC(int offset, int pageSize, String field) {
		String methodName = "getAllEmployeeswithSortAndPagiASC";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Page<Employee> employees = empRepository
					.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, field)));
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return employees;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting Employee without pagination and pagination ase"
					+ methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	/**
	 * getting the all Old Employees with pagination and descending order
	 */
	@Override
	public Page<Employee> getAllEmployeeswithSortAndPagiDelDesc(boolean isEmpDeleted, int offset, int pageSize,
			String field) {
		String methodName = "getAllEmployeeswithSortAndPagiDelDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Page<Employee> employees = empRepository.findByEmpWithDeletedStatus(isEmpDeleted,
					PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, field)));
			return employees;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting old  Employees without pagination and pagination ase"
					+ methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	/**
	 * getting the all Old Employees with pagination and descending order
	 */
	@Override
	public Page<Employee> getAllEmployeeswithSortAndPagiDelASC(boolean isEmpDeleted, int offset, int pageSize,
			String field) {
		String methodName = "getAllEmployeeswithSortAndPagiDelDesc";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Page<Employee> employees = empRepository.findByEmpWithDeletedStatus(isEmpDeleted,
					PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.ASC, field)));
			return employees;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting old Employees without pagination and pagination ase"
					+ methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	/**
	 * getting all employee details by search term
	 */
	@Override
	public Page<Employee> searchEmpsByParam(String searchParam, int offset, int pageSize) {
		String methodName = "searchEmpsByParam";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Page<Employee> searchEmps = empRepository.searchEmpsBySearchParam(searchParam,
					PageRequest.of(offset, pageSize));
			LOGGER.info(CLASSNAME + ": Existing into the " + methodName);
			return searchEmps;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while searching the Employees" + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}
}
