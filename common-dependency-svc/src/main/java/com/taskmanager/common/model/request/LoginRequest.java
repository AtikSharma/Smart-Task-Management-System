package com.taskmanager.common.model.request;

import java.io.Serializable;

import org.springframework.lang.Nullable;

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
public class LoginRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Schema(description = "Username of the user", example = "john_doe")
	private String username;

	@Nullable
	@Schema(description = "Email address of the user", example = "john@example.com")
	private String email;

	@Schema(description = "Password of the user", example = "securePassword123")
	private String password;

}
