package com.taskmanager.common.util;

public class StringUtils {

	public static final String EMPTY = "";

	/**
	 * Check if string is blank
	 * 
	 * @param string
	 * @return true if null or empty or contains only whitespaces
	 */
	public static boolean isBlank(String string) {
		return string != null && string.isBlank();
	}
}
