package com.vinnotech.portal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinnotech.portal.model.Address;
import com.vinnotech.portal.service.AddressService;

@CrossOrigin
@RestController
@RequestMapping("/api/address")
public class AddressController {

	private static final String CLASSNAME = "AddressController";
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);
	@Autowired
	private AddressService addressService;

	@PutMapping("/permanent/{empId}")
	public void addPAddress(@RequestBody Address address, @PathVariable Long empId) {
		String methodName = "addPAddress";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		addressService.addPAddress(address, empId);
	}

	@PutMapping("/current/{empId}")
	public void addCAddress(@RequestBody Address address, @PathVariable Long empId) {

		String methodName = "addCAddress";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		addressService.addCAddress(address, empId);
	}
}
