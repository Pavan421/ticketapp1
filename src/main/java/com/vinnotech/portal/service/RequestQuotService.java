package com.vinnotech.portal.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vinnotech.portal.exception.HRPortalException;
import com.vinnotech.portal.model.RequestQuot;
import com.vinnotech.portal.repository.RequestQuotRepository;

@Service
public class RequestQuotService {

	private static final String CLASSNAME = "RequestQuotService";
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestQuotService.class);

	@Autowired
	private RequestQuotRepository requestQuotRepository;

	public RequestQuot createRequestQuot(RequestQuot requestQuot) {

		String methodName = "createRequestQuot";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
		return requestQuotRepository.save(requestQuot);
		}catch (Exception e) {
			LOGGER.error(CLASSNAME + "got error while creating request Quots " + methodName + e.getMessage());
			throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
		 }
	}

	public List<RequestQuot> getAllRequestQuots() {

		String methodName = "getAllRequestQuots";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		try {
		return requestQuotRepository.findAll();
	}catch (Exception e) {
		LOGGER.error(CLASSNAME + "got error while getting request Quots " + methodName + e.getMessage());
		throw new HRPortalException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getCause().getMessage());
	   }
	}
}
