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
public class ServiceResponse extends BaseResponse {

	private String title;

	public ResponseEntity<Object> build(String title, HttpStatus status) {
		setStatus(status.value());
		setTitle(title);
		setCorrelationId(RequestContext.getCorrelationId());
		return new ResponseEntity<Object>(this, status);
	}

	public <T> ResponseEntity<T> build(String title, HttpStatus status, T responseBody) {
		setStatus(status.value());
		setTitle(title);
		setCorrelationId(RequestContext.getCorrelationId());
		return new ResponseEntity<>(responseBody, status);
	}

}
