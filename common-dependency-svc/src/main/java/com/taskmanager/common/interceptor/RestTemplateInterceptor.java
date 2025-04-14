package com.taskmanager.common.interceptor;

import java.io.IOException;

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
		logger.debug("Intercepting Http Call : " + request.getURI().toString());
		HttpHeaders requestHeaders = request.getHeaders();
		requestHeaders.set(RequestContext.HEADER_FIELD_CORRELATION_ID, RequestContext.getCorrelationId());
		requestHeaders.set(RequestContext.HEADER_FIELD_AUTHORIZATION, RequestContext.getAuthorizationToken());
		return execution.execute(request, body);
	}

}
