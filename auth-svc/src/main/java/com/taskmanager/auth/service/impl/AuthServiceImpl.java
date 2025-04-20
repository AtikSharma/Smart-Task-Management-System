package com.taskmanager.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanager.auth.mapper.UserBOMapper;
import com.taskmanager.auth.service.AuthService;
import com.taskmanager.common.client.UserServiceClient;
import com.taskmanager.common.model.UserBase;
import com.taskmanager.common.model.request.RegistrationRequest;

@Service
public class AuthServiceImpl implements AuthService {

	private UserServiceClient userServiceClient;
	private UserBOMapper userBOMapper;

	@Autowired
	AuthServiceImpl(UserServiceClient userServiceClient, UserBOMapper userBOMapper) {
		this.userServiceClient = userServiceClient;
		this.userBOMapper = userBOMapper;
	}

	@Override
	public UserBase registerUser(UserBase userBase) {
		RegistrationRequest request = userBOMapper.mapToRegistrationRequestFromUserBase(userBase);
		return userServiceClient.registerUser(request);
	}

}
