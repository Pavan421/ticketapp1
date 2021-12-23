package com.vinnotech.portal.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.model.ContactUS;
import com.vinnotech.portal.repository.ContactusRepository;

@Service
public class ContactusService {
	private static final String CLASSNAME = "ContactusService";
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactusService.class);

	@Autowired
	private ContactusRepository contactusRepository;

	public ContactUS addContactus(ContactUS contactus) {
		String methodName = "addContactus";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		return contactusRepository.save(contactus);
	}

	public List<ContactUS> getAllContactus() {
		String methodName = "getAllContactus";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		return contactusRepository.findAll();
	}
}
