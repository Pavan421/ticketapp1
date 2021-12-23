package com.vinnotech.portal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinnotech.portal.model.ContactUS;
import com.vinnotech.portal.service.ContactusService;

@RestController
@RequestMapping("api/contactus")

public class ContactusController {

	private static final String CLASSNAME = "ContactusController";
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactusController.class);

	@Autowired
	private ContactusService contactusService;

	@PostMapping
	public ResponseEntity<ContactUS> saveContactUS(@RequestBody ContactUS contactus) {
		String methodName = "saveContactUS";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		return new ResponseEntity<ContactUS>(contactusService.addContactus(contactus), HttpStatus.CREATED);
	}

	@GetMapping
	public List<ContactUS> contactus() {
		String methodName = "contactus";
		LOGGER.info(CLASSNAME + ":Entering into the" + methodName + " method");
		return contactusService.getAllContactus();
	}
}
