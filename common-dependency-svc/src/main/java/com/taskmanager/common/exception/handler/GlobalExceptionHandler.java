package com.taskmanager.common.exception.handler;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.taskmanager.common.exception.RestCallException;
import com.taskmanager.common.model.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RestCallException.class)
	public @ResponseBody ResponseEntity<ErrorResponse> handleException(RestCallException exception,
			HttpServletRequest request) {
		return new ResponseEntity<>(exception.getErrorResponse(),
				HttpStatus.valueOf(exception.getErrorResponse().getStatus()));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public @ResponseBody ResponseEntity<ErrorResponse> handleException(DataIntegrityViolationException exception,
			HttpServletRequest request) {
		return new ErrorResponse().build("Data Error", exception.getMostSpecificCause().toString(),
				request.getRequestURL().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public @ResponseBody ResponseEntity<ErrorResponse> handleException(Exception exception,
			HttpServletRequest request) {
		exception.printStackTrace();
		return new ErrorResponse().build(ExceptionUtils.getRootCauseMessage(exception),
				ExceptionUtils.getMessage(exception), request.getRequestURL().toString(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
