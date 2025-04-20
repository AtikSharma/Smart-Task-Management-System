package com.taskmanager.common.exception;

import com.taskmanager.common.model.ErrorResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestCallException extends RuntimeException {

	public RestCallException(Exception e) {
		super(e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8172322329547146232L;
	private ErrorResponse errorResponse;
}
