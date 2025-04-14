package com.taskmanager.common.model;

import com.taskmanager.common.enums.Role;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class UserBase {

	private String id;

	private String username;

	private String email;

	private String password;

	private Role role;
}
