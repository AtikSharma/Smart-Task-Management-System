package com.taskmanager.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.taskmanager.common.RequestContext;

public class CustomSecurityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(CustomSecurityException.class);

	private final HttpStatus status;

	public CustomSecurityException(HttpStatus status) {
		super(status.getReasonPhrase());
		this.status = status;
	}

	public CustomSecurityException(HttpStatus status, String message) {
		super(message);
		this.status = status;
		logException(message, null);
	}

	public CustomSecurityException(HttpStatus status, String message, Exception cause) {
		super(message, cause);
		this.status = status;
		logException(message, cause);
	}

	private void logException(String message, Throwable cause) {
		logger.error("Security exception occurred - Status: {}, Message: {}, Exception: {}", status, message, cause);
	}

	public HttpStatus getStatus() {
		return status;
	}
}
