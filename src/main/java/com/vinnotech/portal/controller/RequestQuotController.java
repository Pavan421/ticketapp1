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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinnotech.portal.model.HRPortalConstants;
import com.vinnotech.portal.model.RequestQuot;
import com.vinnotech.portal.service.RequestQuotService;

@CrossOrigin
@RestController
@RequestMapping("/api/reqquot")
public class RequestQuotController {

	private static final String CLASSNAME = "RequestQuotController";
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestQuotController.class);
	
	@Autowired
	private RequestQuotService requestQuotService;

	@PostMapping("/create")
	public ResponseEntity<RequestQuot> createRequestQuot(@RequestBody RequestQuot requestQuot) {
		String methodName = "createRequestQuot";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		RequestQuot  createReqQuot = requestQuotService.createRequestQuot(requestQuot);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "creating Request quot");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(createReqQuot);
	}
    
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@GetMapping
	public ResponseEntity<List<RequestQuot>> getAllRequestQuots() {
		String methodName = "getAllRequestQuots";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		List<RequestQuot>  getReqQuot = requestQuotService.getAllRequestQuots();
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "creating Request quot");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(getReqQuot);		
	}
}
