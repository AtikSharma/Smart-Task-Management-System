package com.taskmanager.common.client;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.taskmanager.common.RequestContext;
import com.taskmanager.common.annotation.RestCallExceptionHandler;
import com.taskmanager.common.constants.CommonConstants;
import com.taskmanager.common.model.User;
import com.taskmanager.common.model.UserBase;
import com.taskmanager.common.model.request.RegistrationRequest;
import com.taskmanager.common.util.JwtUtils;
import com.taskmanager.common.util.URLBuilder;

@Component
public class UserServiceClientImpl implements UserServiceClient {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Value(value = "${taskmanager.userservice.instance.name:" + CommonConstants.USER_SVC + "}")
	private String serviceName;

	private RestTemplate restTemplate;
	private final JwtUtils jwtUtils;

	@Autowired
	public UserServiceClientImpl(RestTemplate restTemplate, JwtUtils jwtUtils) {
		this.restTemplate = restTemplate;
		this.jwtUtils = jwtUtils;
	}

	@Override
	@RestCallExceptionHandler
	public UserBase registerUser(RegistrationRequest registrationRequest) {
		String url = URLBuilder.builder().protocol("http").serviceName(serviceName)
				.addPathSegment(CommonConstants.BASE_URL_USER).addPathSegment(CommonConstants.REGISTER).build();
		RequestContext.setAuthorizationToken(jwtUtils.generateSystemToken());
		HttpEntity<RegistrationRequest> requestEntity = new HttpEntity<>(registrationRequest);
		return restTemplate.exchange(URI.create(url), HttpMethod.POST, requestEntity, UserBase.class).getBody();
	}

	@Override
	public User getUserDetails(String identifier) {
		String url = URLBuilder.builder().protocol("http").serviceName(serviceName)
				.addPathSegment(CommonConstants.BASE_URL_USER).addPathSegment(CommonConstants.USER)
				.addPathSegment(identifier).build();
		RequestContext.setAuthorizationToken(jwtUtils.generateSystemToken());
		return restTemplate.exchange(URI.create(url), HttpMethod.GET, HttpEntity.EMPTY, User.class).getBody();
	}

}
