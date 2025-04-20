package com.taskmanager.common.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class URLBuilderTest {

	@Test
	void test() {
		String url = URLBuilder.builder().protocol("http").serviceName("user-service").addPathSegment("api")
				.addPathSegment("users").addPathSegment("registration").build();
		assertEquals("http://user-service/api/users/registration", url);
	}

}
