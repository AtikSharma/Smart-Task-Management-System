package com.taskmanager.common.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.taskmanager.common.RequestContext;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServiceResponse {

	private String title;
	private HttpStatus status;
	private String correlationId;

	public ResponseEntity<Object> build(String title, HttpStatus status) {
		this.status = status;
		this.title = title;
		this.correlationId = RequestContext.getCorrelationId();
		return new ResponseEntity<Object>(this, status);
	}

	public <T> ResponseEntity<T> build(String title, HttpStatus status, T responseBody) {
		this.title = title;
		this.status = status;
		this.correlationId = RequestContext.getCorrelationId();
		return new ResponseEntity<>(responseBody, status);
	}

}
