package com.vinnotech.portal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.model.Address;
import com.vinnotech.portal.model.Employee;
import com.vinnotech.portal.repository.AddressRepository;
import com.vinnotech.portal.repository.EmployeeRepository;

@Service
public class AddressService {

	private static final String CLASSNAME = "AddressService";
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressService.class);

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public void addPAddress(Address address, Long empId) {

		String methodName = "addPAddress";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		Employee emp = employeeRepository.findById(empId).get();
		emp.setPermanentAddress(address);
		employeeRepository.save(emp);
	}

	public void addCAddress(Address address, Long empId) {

		String methodName = "addCAddress";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		Employee emp = employeeRepository.findById(empId).get();
		emp.setCurrentAddress(address);
		employeeRepository.save(emp);
	}
}
