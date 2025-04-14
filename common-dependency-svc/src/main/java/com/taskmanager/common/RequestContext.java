package com.taskmanager.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestContext {

	public static final String HEADER_FIELD_AUTHORIZATION = "Authorization";
	public static final String HEADER_FIELD_CORRELATION_ID = "Correlation-Id";
	public static final String LOG_FIELD_CORRELATION_ID = "correlationId";

	private static final ThreadLocal<String> CORRELATION_ID_HOLDER = new ThreadLocal<>();
	private static final ThreadLocal<String> AUTHORIZATION_TOKEN_HOLDER = new ThreadLocal<>();

	public static void setCorrelationId(String string) {
		CORRELATION_ID_HOLDER.set(string);
	}

	public static String getCorrelationId() {
		return CORRELATION_ID_HOLDER.get();
	}

	public static void setAuthorizationToken(String string) {
		AUTHORIZATION_TOKEN_HOLDER.set(string);
	}

	public static String getAuthorizationToken() {
		return AUTHORIZATION_TOKEN_HOLDER.get();
	}
}
