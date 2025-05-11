package com.taskmanager.common.util;

import java.util.UUID;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class StringUtils {

	public static final String EMPTY = "";

	private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

	/**
	 * Check if string is blank
	 * 
	 * @param string
	 * @return true if null or empty or contains only whitespaces
	 */
	public static boolean isBlank(String string) {
		return string != null && string.isBlank();
	}

	public static Predicate<String> isValidEmail = (email) -> {
		return !isBlank(email) && EMAIL_PATTERN.matcher(email).matches();
	};

	public static Predicate<String> isValidUUID = (uuid) -> {
		try {
			UUID.fromString(uuid);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	};
}
