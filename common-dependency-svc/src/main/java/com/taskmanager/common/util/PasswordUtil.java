package com.taskmanager.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {

	private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public static String encrypt(String data) {
		return passwordEncoder.encode(data);
	}

	public static boolean isMatch(String password, String storedHashedPassword) {
		return passwordEncoder.matches(password, storedHashedPassword);
	}

}
