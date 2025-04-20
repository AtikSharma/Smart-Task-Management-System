package com.taskmanager.usermanagement.mapper;

import org.mapstruct.Mapper;

import com.taskmanager.common.model.User;
import com.taskmanager.usermanagement.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

	UserEntity mapTo(User user);

	User mapFrom(UserEntity userEntity);
}
