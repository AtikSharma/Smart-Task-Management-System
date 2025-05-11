package com.taskmanager.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonConstants {

	public static final String BASE_PACKAGE = "com.taskmanager";

	public static final String AUTH_SVC = "AUTH-SVC";
	public static final String USER_SVC = "USER-SVC";
	public static final String NOTIFY_SVC = "NOTIFY-SVC";
	public static final String FILE_SVC = "FILE-SVC";
	public static final String TASK_SVC = "TASK-SVC";
	public static final String GATEWAY_SVC = "GATEWAY-SVC";
	public static final String REPORT_SVC = "REPORT-SVC";

	public static final String FORWARD_SLASH = "/";

	public static final String V1 = "v1";
	public static final String API_PATH = "api";
	public static final String USERS_PATH = "users";
	public static final String TASKS_PATH = "tasks";
	public static final String AUTH_PATH = "auth";

	public static final String REGISTER = "register";
	public static final String USER = "user";
	public static final String LOGIN = "login";

	public static final String BASE_URL = API_PATH + FORWARD_SLASH + V1;
	public static final String BASE_URL_USER = BASE_URL + FORWARD_SLASH + USERS_PATH;

	public static final String USERS_API_REGISTER = FORWARD_SLASH + REGISTER;
	public static final String USERS_API_USER = FORWARD_SLASH + USER + FORWARD_SLASH;
	public static final String AUTH_API_LOGIN = FORWARD_SLASH + LOGIN;

	public static final String SPACE = " ";
	public static final String BEARER = "Bearer";
	public static final String DEFAULT_AUTHORIZATION = "Bearer {accessToken}";

}
