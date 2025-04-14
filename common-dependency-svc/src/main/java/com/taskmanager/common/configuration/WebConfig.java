package com.taskmanager.common.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.taskmanager.common.interceptor.CustomRequestInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private CustomRequestInterceptor customRequestInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(customRequestInterceptor).addPathPatterns("/api/**") // Apply to specific endpoints
				.excludePathPatterns("/api/auth/**"); // Exclude specific endpoints
	}
}
