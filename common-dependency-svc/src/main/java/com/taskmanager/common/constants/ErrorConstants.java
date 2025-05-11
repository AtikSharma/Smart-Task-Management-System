package com.taskmanager.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorConstants {

	public static final String ERROR_INVALID_IDENTIFIER = "Invalid Identifier : ";
	public static final String ERROR_USER_NOT_FOUND = "User Doesn't Exists with identifier : ";
	public static final String ERROR_INVALID_USERNAME_OR_PASSWORD = "Invalid UserName or Email";
	public static final String ERROR_WHILE_FINDING_USER = "Error Occured while Finding User Details";
}
