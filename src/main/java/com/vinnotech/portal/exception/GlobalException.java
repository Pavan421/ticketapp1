package com.vinnotech.portal.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(value = TokenRefreshException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ErrorMessage handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
		return new ErrorMessage(HttpStatus.FORBIDDEN.value(), new Date(), ex.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler(value = HRPortalException.class)
	public ResponseEntity<ErrorMessage> handleHRPortalException(HRPortalException ex, WebRequest request) {
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "Exception");
		ErrorMessage errormsg = new ErrorMessage(ex.getStatusCode(), new Date(), ex.getMessage().split(":")[0],
				request.getDescription(false));
		return ResponseEntity.status(ex.getStatusCode()).headers(header).body(errormsg);
	}
}
