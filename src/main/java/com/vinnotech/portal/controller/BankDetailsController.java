package com.vinnotech.portal.controller;

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

import com.vinnotech.portal.model.BankDetails;
import com.vinnotech.portal.model.HRPortalConstants;
import com.vinnotech.portal.service.BankDetailsService;

@CrossOrigin
@RestController
@RequestMapping("/api/bankdetails")
public class BankDetailsController {
	
	private static final String CLASSNAME = "BankDetailsController";
	private static final Logger LOGGER = LoggerFactory.getLogger(BankDetailsController.class);

	@Autowired
	private BankDetailsService bankDetailsService;
	
	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@PutMapping("/update/{empId}")
	public ResponseEntity<String> updateBankDetails(@RequestBody BankDetails bankDetails,
			@PathVariable("empId") Long empId) {
		String methodName = "updateBankDetails";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		String updatedBankDetails = bankDetailsService.updateBankDetails(bankDetails, empId);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "updating bankdetails");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(updatedBankDetails);
	}

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@GetMapping("/{empId}")
	public ResponseEntity<BankDetails> getBankDetails(@PathVariable("empId") Long empId) {
		String methodName = "getBankDetails";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName + " method");
		BankDetails empBankDetails = bankDetailsService.getBankDetails(empId);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "getting bankdetails");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(empBankDetails);
	}
}
