package com.vinnotech.portal.controller;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinnotech.portal.model.ChangePwd;
import com.vinnotech.portal.model.HRPortalConstants;
import com.vinnotech.portal.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

	private static final String CLASSNAME = "UserController";
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@GetMapping("/resetpassword/{username}")
	public ResponseEntity<String> forgotPassword(@PathParam("username") String username) {
		String methodName = "forgotPassword";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		String successResponse = userService.forgotPassword(username);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Resetting password");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(successResponse);
	}

	@PreAuthorize(HRPortalConstants.ROLE_ADMIN_HR_ONLY)
	@PutMapping("/changepassword")
	public ResponseEntity<String> changePassword(@RequestBody ChangePwd changePwd) {
		String methodName = "changePassword";
		LOGGER.info(CLASSNAME + ": Entering into the " + methodName);
		String successResponse = userService.changePassword(changePwd);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Changing password");
		LOGGER.info(CLASSNAME + ": Existing from  " + methodName + " method");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(successResponse);
	}
}
