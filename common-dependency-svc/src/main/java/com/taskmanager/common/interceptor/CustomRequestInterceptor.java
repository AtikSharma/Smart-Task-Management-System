package com.taskmanager.common.interceptor;

import java.time.Duration;
import java.time.LocalDateTime;

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

	private LocalDateTime preHandleTimeStamp;
	private LocalDateTime postHandleTimeStamp;
	private LocalDateTime completionTimeStamp;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		preHandleTimeStamp = LocalDateTime.now();
		RequestContext.resolveCorrelationId(request);
		RequestContext.resolveAuthorizationToken(request);
		logger.debug("{" + RequestContext.getCorrelationId() + "} : " + request.getMethod() + " : "
				+ request.getRequestURI());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
		postHandleTimeStamp = LocalDateTime.now();
		logger.debug("PostHandle: Processing request completed in : "
				+ Duration.between(preHandleTimeStamp, postHandleTimeStamp).toMillis() + " milliseconds");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		completionTimeStamp = LocalDateTime.now();
		logger.debug("AfterCompletion: Request processing completed in : "
				+ Duration.between(preHandleTimeStamp, completionTimeStamp).toMillis() + " milliseconds");
	}
}
