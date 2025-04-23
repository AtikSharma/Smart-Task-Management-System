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

import com.taskmanager.common.exception.RestCallException;
import com.taskmanager.common.model.ErrorResponse;
import com.taskmanager.common.util.CommonUtility;

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
			if (response == null) {
				response = new ErrorResponse().buildError(ExceptionUtils.getMessage(exception), "Something went wrong",
						CommonUtility.getRequestUrl(), HttpStatus.valueOf(ex.getStatusCode().value()));
			}
			exception.setErrorResponse(response);
			throw exception;
		} catch (ResourceAccessException ex) {
			RestCallException exception = new RestCallException(ex);
			exception.setErrorResponse(new ErrorResponse().buildError(ExceptionUtils.getMessage(exception),
					"Unable to access the resource", CommonUtility.getRequestUrl(), HttpStatus.SERVICE_UNAVAILABLE));
			throw exception;
		} catch (Exception ex) {
			RestCallException exception = new RestCallException(ex);
			exception.setErrorResponse(new ErrorResponse().buildError(ExceptionUtils.getMessage(exception),
					"Something went wrong", CommonUtility.getRequestUrl(), HttpStatus.INTERNAL_SERVER_ERROR));
			throw exception;
		}
	}

}
