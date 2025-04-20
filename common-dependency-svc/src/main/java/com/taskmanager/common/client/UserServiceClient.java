package com.taskmanager.common.client;

import com.taskmanager.common.model.UserBase;
import com.taskmanager.common.model.request.RegistrationRequest;

public interface UserServiceClient {

	public UserBase registerUser(RegistrationRequest registrationRequest);

}
