package com.taskmanager.common.configuration;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import com.taskmanager.common.RequestContext;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;

@Component
@ConditionalOnExpression("${com.taskmanager.swagger.autoconfig:false}")
public class SwaggerCustomizer implements OperationCustomizer {

	private static final String EXAMPLE_CORRELATION_HEADER = "d64cf01b-ce65-4a57-ac3e-f7fa09e1a87f";

	@Override
	public Operation customize(Operation operation, HandlerMethod handlerMethod) {
		Parameter correlationHeaderVersion = new Parameter().in(ParameterIn.HEADER.toString())
				.name(RequestContext.HEADER_FIELD_CORRELATION_ID).schema(new StringSchema())
				.example(EXAMPLE_CORRELATION_HEADER).required(true);
		operation.addParametersItem(correlationHeaderVersion);
		return operation;
	}
}
