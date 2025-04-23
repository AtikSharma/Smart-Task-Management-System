package com.taskmanager.common.model;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import com.taskmanager.common.RequestContext;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse extends ProblemDetail {

	private static final long serialVersionUID = 1L;
	private String correlationId;

	public ResponseEntity<ErrorResponse> build(String detail, String title, String requestUrl, HttpStatus status) {
		setStatus(status.value());
		setCorrelationId(RequestContext.getCorrelationId());
		setTitle(title);
		setDetail(detail);
		setInstance(URI.create(requestUrl));
		return new ResponseEntity<ErrorResponse>(this, status);
	}

	public ErrorResponse buildError(String detail, String title, String requestUrl, HttpStatus status) {
		setStatus(status.value());
		setCorrelationId(RequestContext.getCorrelationId());
		setTitle(title);
		setDetail(detail);
		setInstance(URI.create(requestUrl));
		return this;
	}

}
