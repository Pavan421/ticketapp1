package com.vinnotech.portal.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return requestQuotRepository.save(requestQuot);
	}

	public List<RequestQuot> getAllRequestQuots() {

		String methodName = "getAllRequestQuots";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		return requestQuotRepository.findAll();
	}
}
