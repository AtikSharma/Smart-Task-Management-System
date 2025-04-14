package com.taskmanager.common.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import com.taskmanager.common.interceptor.RestTemplateInterceptor;

@Configuration
public class RestTemplateConfig {

	@Autowired
	private RestTemplateInterceptor restTemplateInterceptor;

	@Bean
	RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		interceptors.add(restTemplateInterceptor);
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

}
