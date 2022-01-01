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

import com.vinnotech.portal.model.HRPortalConstants;
import com.vinnotech.portal.model.PreviousCompany;
import com.vinnotech.portal.service.PreviousCompanyService;

@CrossOrigin
@RestController
@RequestMapping("/api/previouscompany")
public class PreviousCompanyController {

	private static final String CLASSNAME = "PreviousCompanyController";
	private static final Logger LOGGER = LoggerFactory.getLogger(PreviousCompanyController.class);

	@Autowired
	private PreviousCompanyService previousCompanyService;

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@PutMapping("/update/{empId}")
	public ResponseEntity<String> updatePreviousCompanyDetails(@RequestBody List<PreviousCompany> previousCompany,
			@PathVariable("empId") Long empId) {
		String methodName = "updatePreviousCompanyDetails";
		LOGGER.info(CLASSNAME + " entering into the " + methodName + " method");
		String updatedPreviousCompanyDetails = previousCompanyService.updatePreviousCompanyDetails(previousCompany,
				empId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Updating PreviousCompanyDetails");
		LOGGER.info(CLASSNAME + " existing from " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(updatedPreviousCompanyDetails);
	}

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@GetMapping("/{empId}")
	public ResponseEntity<List<PreviousCompany>> getPreviousCompanyDetails(@PathVariable("empId") Long empId) {
		String methodName = "getPreviousCompanyDetails";
		LOGGER.info(CLASSNAME + " entering into the " + methodName + " method");
		List<PreviousCompany> previousCompanyDetails = previousCompanyService.getPreviousCompanyDetails(empId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "getting PreviousCompanyDetails");
		LOGGER.info(CLASSNAME + " existing from " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(previousCompanyDetails);
	}
}
