package com.vinnotech.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.exception.HRPortalException;
import com.vinnotech.portal.model.Address;
import com.vinnotech.portal.model.Employee;
import com.vinnotech.portal.repository.EmployeeRepository;

@Service
public class AddressService {

	private static final String CLASSNAME = "AddressService";
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressService.class);

	@Autowired
	private EmployeeRepository employeeRepository;
 
	public String addPAddress(Address address, Long empId) {
		String methodName = "addPAddress";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
		Employee emp = employeeRepository.findById(empId).get();
		emp.setPermanentAddress(address);
		employeeRepository.save(emp);
		LOGGER.info(CLASSNAME + ": Existing into the " + methodName);
		return "Permanent Address added Successfully";
		}catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while creating Course " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		}
	}

	public String addCAddress(Address address, Long empId) {
		String methodName = "addCAddress";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
		Employee emp = employeeRepository.findById(empId).get();
		emp.setCurrentAddress(address);
		employeeRepository.save(emp);
		LOGGER.info(CLASSNAME + ": Existing into the " + methodName);
		return "Current Address added Successfully";
		}catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while creating Course " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		}
	}
	
	public List<Address> getEmpAddress(Long empId) {
		String methodName = "getEmpAddress";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
			Employee emp=employeeRepository.findById(empId).get();
			Address empPAddress=emp.getPermanentAddress();
			Address empCAddress=emp.getCurrentAddress();
			List<Address> empAddressList = new ArrayList<Address>();
			empAddressList.add(empPAddress);
			empAddressList.add(empCAddress);
			LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
			return empAddressList;
		}catch(Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting Employee Address" + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		}
	}
}
