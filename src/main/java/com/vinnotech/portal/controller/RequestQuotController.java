package com.vinnotech.portal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinnotech.portal.model.RequestQuot;
import com.vinnotech.portal.service.RequestQuotService;

@CrossOrigin
@RestController
@RequestMapping("/api/reqquot")
public class RequestQuotController {

	private static final String CLASSNAME = "RequestQuotController";
	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);
	@Autowired
	private RequestQuotService requestQuotService;

	@PostMapping
	public RequestQuot createRequestQuot(@RequestBody RequestQuot requestQuot) {

		String methodName = "createRequestQuot";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		return requestQuotService.createRequestQuot(requestQuot);
	}

	public List<RequestQuot> getAllRequestQuots() {

		String methodName = "getAllRequestQuots";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		return requestQuotService.getAllRequestQuots();
	}

}
