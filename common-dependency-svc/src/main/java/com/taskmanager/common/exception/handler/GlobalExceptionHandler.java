package com.taskmanager.common.exception.handler;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.taskmanager.common.exception.ApplicationException;
import com.taskmanager.common.exception.RestCallException;
import com.taskmanager.common.model.ErrorResponse;
import com.taskmanager.common.util.CommonUtility;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RestCallException.class)
	public @ResponseBody ResponseEntity<ErrorResponse> handleException(RestCallException exception) {
		return new ResponseEntity<>(exception.getErrorResponse(),
				HttpStatus.valueOf(exception.getErrorResponse().getStatus()));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public @ResponseBody ResponseEntity<ErrorResponse> handleException(DataIntegrityViolationException exception) {
		return new ErrorResponse().build(exception.getMostSpecificCause().toString(), "Data Error",
				CommonUtility.getRequestUrl(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public @ResponseBody ResponseEntity<ErrorResponse> handleMethodNotSupportedException(
			HttpRequestMethodNotSupportedException exception) {
		return new ErrorResponse().build(exception.getMessage(), "Method Not Allowed", CommonUtility.getRequestUrl(),
				HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(ApplicationException.class)
	public @ResponseBody ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException exception) {
		return new ErrorResponse().build(exception.getLocalizedMessage() + exception.getParamsAsString(),
				exception.getLocalizedMessage(), CommonUtility.getRequestUrl(),
				exception.getStatus() != null ? exception.getStatus() : HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public @ResponseBody ResponseEntity<ErrorResponse> handleException(Exception exception) {
		exception.printStackTrace();
		return new ErrorResponse().build(ExceptionUtils.getRootCauseMessage(exception),
				ExceptionUtils.getMessage(exception), CommonUtility.getRequestUrl(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
