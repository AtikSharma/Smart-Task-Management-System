package com.taskmanager.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorConstants {

	public static final String ERROR_INVALID_IDENTIFIER = "Invalid Identifier : ";
	public static final String ERROR_USER_NOT_FOUND = "User Doesn't Exists with identifier : ";
	public static final String ERROR_INVALID_USERNAME_OR_PASSWORD = "Invalid UserName or Email";
	public static final String ERROR_WHILE_FINDING_USER = "Error Occured while Finding User Details";
	public static final String ROLE_UNDEFINED = "Unable to determine role";
	public static final String ACCESS_DENIED = "Access Denied: User does not have the required role";
	public static final String SYSTEM_ACCESS_TOKEN_EXPIRED = "System Access Token Expired";
	public static final String INVALID_TOKEN = "Token missing or invalid format.";
	public static final String ACCESS_TOKEN_EXPIRED = "Access token expired for user: %s";
	public static final String INACTIVE_USER = "Inactive Account or Account Blocked";
	public static final String INCORRECT_PASSWORD = "Incorrect Password";
}
