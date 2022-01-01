package com.vinnotech.portal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinnotech.portal.model.Address;
import com.vinnotech.portal.model.HRPortalConstants;
import com.vinnotech.portal.service.AddressService;

@CrossOrigin
@RestController
@RequestMapping("/api/address")
public class AddressController {

	private static final String CLASSNAME = "AddressController";
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);

	@Autowired
	private AddressService addressService;

	/**
	 * Adding permanent Address
	 * 
	 * @param address
	 * @param empId
	 * @return
	 */

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@PutMapping("/permanent/{empId}")
	public ResponseEntity<String> addPAddress(@RequestBody Address address, @PathVariable("empId") Long empId) {
		String methodName = "addPAddress";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		String empPAddessAdded = addressService.addPAddress(address, empId);
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "adding employee Permanent Address");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(empPAddessAdded);
	}

	/**
	 * Adding Current Address
	 * 
	 * @param address
	 * @param empId
	 * @return
	 */

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@PutMapping("/current/{empId}")
	public ResponseEntity<String> addCAddress(@RequestBody Address address, @PathVariable("empId") Long empId) {
		String methodName = "addCAddress";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		String empCAddessAdded = addressService.addCAddress(address, empId);
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "adding employee Current Address");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(empCAddessAdded);
	}

	/**
	 * Getting Employee Permanent&Current Address as list
	 * 
	 * @param empId
	 * @return
	 */

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@GetMapping("/{empId}")
	public ResponseEntity<List<Address>> getEmpAddress(@PathVariable("empId") Long empId) {
		String methodName = "getEmpAddress";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		List<Address> empAddress = addressService.getEmpAddress(empId);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting employee address");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(empAddress);
	}

}
