package com.taskmanager.common.client;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.taskmanager.common.annotation.RestCallExceptionHandler;
import com.taskmanager.common.constants.CommonConstants;
import com.taskmanager.common.model.UserBase;
import com.taskmanager.common.model.request.RegistrationRequest;
import com.taskmanager.common.util.URLBuilder;

@Component
public class UserServiceClientImpl implements UserServiceClient {

	@Value(value = "${taskmanager.userservice.instance.name:" + CommonConstants.USER_SVC + "}")
	private String serviceName;

	private RestTemplate restTemplate;

	@Autowired
	public UserServiceClientImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	@RestCallExceptionHandler
	public UserBase registerUser(RegistrationRequest registrationRequest) {
		String url = URLBuilder.builder().protocol("http").serviceName(serviceName).addPathSegment("api")
				.addPathSegment("users").addPathSegment("register").build();
		HttpEntity<RegistrationRequest> requestEntity = new HttpEntity<>(registrationRequest);
		return restTemplate.exchange(URI.create(url), HttpMethod.POST, requestEntity, UserBase.class).getBody();
	}

}
