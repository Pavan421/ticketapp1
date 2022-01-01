package com.vinnotech.portal.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.exception.HRPortalException;
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
		try {
		return contactusRepository.save(contactus);
		}catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while creating Course " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		} 
	}

	public List<ContactUS> getAllContactus() {
		String methodName = "getAllContactus";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
		List<ContactUS> listContactUs = contactusRepository.findAll();
		LOGGER.info(CLASSNAME + ": Existing into the " + methodName);
		return listContactUs;
		}catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while getting course " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getCause().getMessage());
		}
	}
}
