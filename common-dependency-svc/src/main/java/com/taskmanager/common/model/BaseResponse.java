package com.taskmanager.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseResponse {

	@JsonProperty(index = 0)
	private int status;
	@JsonProperty(index = 1)
	private String correlationId;

}
