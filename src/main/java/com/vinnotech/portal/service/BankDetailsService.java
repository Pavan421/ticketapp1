package com.vinnotech.portal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.exception.HRPortalException;
import com.vinnotech.portal.model.BankDetails;
import com.vinnotech.portal.model.Employee;
import com.vinnotech.portal.repository.EmployeeRepository;

@Service
public class BankDetailsService {

	private static final String CLASSNAME = "BankDetailsService";
	private static final Logger LOGGER = LoggerFactory.getLogger(BankDetailsService.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	public String updateBankDetails(BankDetails bankDetails, Long empId) {
		String methodName = "addBankDetails";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Employee emp = employeeRepository.findById(empId).get();
			emp.setBankDetails(bankDetails);
			employeeRepository.save(emp);
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return "BankDetails Updated Successfully";
		} catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while updating BankDetails" + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		}
	}
	
	public BankDetails getBankDetails(Long empId) {
		String methodName = "getBankDetails";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Employee emp=employeeRepository.findById(empId).get();
			BankDetails empBankDetails=emp.getBankDetails();
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return empBankDetails;
		}catch(Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting BankDetails" + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		}
	}
}
