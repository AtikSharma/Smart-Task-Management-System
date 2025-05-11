package com.taskmanager.common.enums;

import java.util.List;

public enum Role {
	ADMIN, MANAGER, USER, SYSTEM,;

	public static List<Role> getRoleList(Role... roles) {
		return List.of(roles);
	}
}
