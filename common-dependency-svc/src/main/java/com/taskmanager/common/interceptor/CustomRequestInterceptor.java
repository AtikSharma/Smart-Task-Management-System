package com.taskmanager.common.interceptor;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.taskmanager.common.RequestContext;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomRequestInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(CustomRequestInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("Request URI: " + request.getRequestURI());
		RequestContext.setCorrelationId(UUID.randomUUID().toString());
		return true; // Continue processing the request
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
		logger.debug("PostHandle: Processing request completed.");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.debug("AfterCompletion: Request processing completed.");
	}
}
