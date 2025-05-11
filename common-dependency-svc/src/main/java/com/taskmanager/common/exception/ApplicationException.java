package com.taskmanager.common.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> params;

	private HttpStatus status;

	public ApplicationException(Exception e) {
		super(e);
	}

	public ApplicationException(String message, String... params) {
		super(message);
		if (params != null) {
			this.params = new ArrayList<>();
			for (String param : params) {
				this.params.add(param);
			}
		}
	}

	public ApplicationException(String message, Exception e) {
		super(message, e);
	}

	public ApplicationException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return this.status;
	}

	public String getParamsAsString() {
		if (params != null) {
			StringBuilder sb = new StringBuilder();
			params.stream().forEach(param -> {
				sb.append(param);
				sb.append(",");
			});
			return sb.toString();
		}
		return "";
	}
}
