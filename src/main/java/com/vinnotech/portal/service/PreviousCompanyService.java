package com.vinnotech.portal.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.exception.HRPortalException;
import com.vinnotech.portal.model.Employee;
import com.vinnotech.portal.model.PreviousCompany;
import com.vinnotech.portal.repository.EmployeeRepository;

@Service
public class PreviousCompanyService {

	private static final String CLASSNAME = "PreviousCompanyService";
	private static final Logger LOGGER = LoggerFactory.getLogger(PreviousCompanyService.class);
	@Autowired
	private EmployeeRepository employeeRepository;

	public String updatePreviousCompanyDetails(List<PreviousCompany> previousCompany, Long empId) {
		String methodName = "updatePreviousCompanyDetails";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Employee emp = employeeRepository.findById(empId).get();
			emp.setPreviousCompany(previousCompany);
			employeeRepository.save(emp);
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return "PreviousCompanyDetails Updated Successfully";
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while updating PreviousCompanyDetails" + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		}
	}
	
	public List<PreviousCompany> getPreviousCompanyDetails(Long empId){
		String methodName = "getPreviousCompanys";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Employee emp = employeeRepository.findById(empId).get();
			List<PreviousCompany> previousCompanies = emp.getPreviousCompany();
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return previousCompanies;
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting PreviousCompanyDetails" + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		}
	}
}
