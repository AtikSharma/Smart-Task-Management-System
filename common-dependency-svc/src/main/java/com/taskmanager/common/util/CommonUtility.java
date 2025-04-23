package com.taskmanager.common.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.taskmanager.common.RequestContext;

import jakarta.servlet.http.HttpServletRequest;

public class CommonUtility {

	public static String getRequestUrl() {
		String requestedUrl = RequestContext.getRequestedUrl();
		if (requestedUrl == null || requestedUrl.isBlank()) {
			RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
			if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
				HttpServletRequest request = servletRequestAttributes.getRequest();
				return request.getRequestURI();
			}
		}
		return requestedUrl;
	}

}
