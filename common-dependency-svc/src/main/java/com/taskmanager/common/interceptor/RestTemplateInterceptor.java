package com.taskmanager.common.interceptor;

import java.io.IOException;
import java.net.URI;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.taskmanager.common.RequestContext;

@Component
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(RestTemplateInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {

		ClientHttpResponse response;

		URI requestedURI = request.getURI();
		String correlationId = RequestContext.getCorrelationId();

		HttpHeaders requestHeaders = request.getHeaders();
		requestHeaders.set(RequestContext.HEADER_FIELD_CORRELATION_ID, correlationId);
		requestHeaders.set(RequestContext.HEADER_FIELD_AUTHORIZATION, RequestContext.getAuthorizationToken());

		RequestContext.setRequestedURL(requestedURI.getPath());

		logger.debug("{" + correlationId + "} : " + request.getMethod() + " : " + requestedURI.toString());
		try {
			response = execution.execute(request, body);
			logger.debug("{" + correlationId + "} : " + request.getMethod() + " : " + response.getStatusCode());
			return response;
		} catch (Exception e) {
			logger.error("Error occured while calling : " + request.getURI().toString() + " : "
					+ ExceptionUtils.getRootCauseMessage(e));
			throw e;
		}
	}

}
