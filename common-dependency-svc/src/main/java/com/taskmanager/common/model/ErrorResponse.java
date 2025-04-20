package com.taskmanager.common.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.taskmanager.common.RequestContext;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse extends BaseResponse {

	private LocalDateTime timestamp;
	private String error;
	private String message;
	private String path;

	public ResponseEntity<ErrorResponse> build(String error, String exceptionMessage, String url, HttpStatus status) {
		setStatus(status.value());
		setCorrelationId(RequestContext.getCorrelationId());
		setMessage(exceptionMessage);
		setError(error);
		setPath(url);
		setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorResponse>(this, status);
	}

	public ErrorResponse buildError(String error, String exceptionMessage, String url, HttpStatus status) {
		setStatus(status.value());
		setCorrelationId(RequestContext.getCorrelationId());
		setMessage(exceptionMessage);
		setError(error);
		setPath(url);
		setTimestamp(LocalDateTime.now());
		return this;
	}

}
