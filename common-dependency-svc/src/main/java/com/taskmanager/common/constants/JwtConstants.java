package com.taskmanager.common.constants;

import com.taskmanager.common.RequestContext;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtConstants {
	public static final String JTI = "jti";
	public static final String TYPE = "typ";
	public static final String SUBJECT = "jwttoken";
	public static final String ISS = "iss";
	public static final String JWT = "JWT";
	public static final String REFRESH = "REFRESH";
	public static final String CORRELATION_ID = RequestContext.HEADER_FIELD_CORRELATION_ID;

	public static final String JWT_ISSUER = "Auth Service";
	public static final String JWT_ISSUER_SYSTEM = "System";

	public static final String ID = "id";
	public static final String USERNAME = "username";
	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String ROLE = "role";

}
