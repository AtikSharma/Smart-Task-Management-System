package com.taskmanager.auth.mapper;

import org.mapstruct.Mapper;

import com.taskmanager.common.model.UserBase;
import com.taskmanager.common.model.UserExp;
import com.taskmanager.common.model.request.RegistrationRequest;

@Mapper(componentModel = "spring")
public interface UserBOMapper {

	public RegistrationRequest mapToRegistrationRequestFromUserBase(UserBase userBase);

	public UserBase mapFromRegistrationRequestToUserBase(RegistrationRequest registrationRequest);

	public UserExp mapToExpFromUserBase(UserBase user);
}
