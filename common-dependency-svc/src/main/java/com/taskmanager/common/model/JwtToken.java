package com.taskmanager.common.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accessToken;
	private String refreshToken;
}
