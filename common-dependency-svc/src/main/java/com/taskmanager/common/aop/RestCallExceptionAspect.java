package com.taskmanager.common.aop;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.taskmanager.common.exception.RestCallException;
import com.taskmanager.common.model.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class RestCallExceptionAspect {

	@Around("@annotation(com.taskmanager.common.annotation.RestCallExceptionHandler)")
	public Object handleRestCallException(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		try {
			return proceedingJoinPoint.proceed();
		} catch (HttpClientErrorException | HttpServerErrorException ex) {
			RestCallException exception = new RestCallException(ex);
			ErrorResponse response = ex.getResponseBodyAs(ErrorResponse.class);
			exception.setErrorResponse(response);
			throw exception;
		} catch (ResourceAccessException ex) {
			RestCallException exception = new RestCallException(ex);
			exception.setErrorResponse(new ErrorResponse().buildError("Unable to access the resource",
					ExceptionUtils.getMessage(exception), getRequestUrl(), HttpStatus.SERVICE_UNAVAILABLE));
			throw exception;
		} catch (Exception ex) {
			RestCallException exception = new RestCallException(ex);
			exception.setErrorResponse(new ErrorResponse().buildError(ExceptionUtils.getMessage(exception),
					ex.getMessage(), getRequestUrl(), HttpStatus.INTERNAL_SERVER_ERROR));
			throw exception;
		}
	}

	private String getRequestUrl() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
			HttpServletRequest request = servletRequestAttributes.getRequest();
			return request.getRequestURL().toString();
		}
		return "UNKNOWN_URL";
	}
}
