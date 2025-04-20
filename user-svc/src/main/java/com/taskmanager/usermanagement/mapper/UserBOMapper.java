package com.taskmanager.usermanagement.mapper;

import org.mapstruct.Mapper;

import com.taskmanager.common.model.User;
import com.taskmanager.common.model.UserBase;
import com.taskmanager.common.model.UserExp;
import com.taskmanager.common.model.request.RegistrationRequest;

@Mapper(componentModel = "spring")
public interface UserBOMapper {

	User mapFromRegistrationRequest(RegistrationRequest registrationRequest);

	UserExp mapToRegistrationResponse(User user);
	
	UserBase mapFrom(User user);
}
