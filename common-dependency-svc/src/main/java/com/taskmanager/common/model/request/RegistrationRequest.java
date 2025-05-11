package com.taskmanager.common.model.request;

import java.io.Serializable;

import com.taskmanager.common.enums.Role;
import com.taskmanager.common.enums.Status;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class RegistrationRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Schema(description = "Username of the user", example = "john_doe")
	private String username;

	@Schema(description = "Email address of the user", example = "john@example.com")
	private String email;

	@Schema(description = "Password of the user", example = "securePassword123")
	private String password;

	@Schema(description = "User role", example = "ADMIN")
	private Role role;
}
