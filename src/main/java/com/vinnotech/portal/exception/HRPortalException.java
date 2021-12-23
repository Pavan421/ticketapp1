package com.vinnotech.portal.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class HRPortalException extends RuntimeException {
	private int statusCode;
	private String message;
	private String errorCause;
	
}
