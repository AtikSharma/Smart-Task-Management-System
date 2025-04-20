package com.taskmanager.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.auth.mapper.UserBOMapper;
import com.taskmanager.auth.model.response.RegistrationResponse;
import com.taskmanager.auth.service.AuthService;
import com.taskmanager.common.model.UserBase;
import com.taskmanager.common.model.UserExp;
import com.taskmanager.common.model.request.RegistrationRequest;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

	private AuthService authService;

	private UserBOMapper userBOMapper;

	@Autowired
	public AuthController(AuthService authService, UserBOMapper userBOMapper) {
		this.authService = authService;
		this.userBOMapper = userBOMapper;
	}

	@PostMapping(path = "/register")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "User Registration API", content = @Content(schema = @Schema(implementation = RegistrationResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)) })
	public ResponseEntity<RegistrationResponse> registration(@RequestBody RegistrationRequest registrationRequest) {
		UserBase user = userBOMapper.mapFromRegistrationRequestToUserBase(registrationRequest);
		user = authService.registerUser(user);
		UserExp userExp = userBOMapper.mapToExpFromUserBase(user);
		RegistrationResponse response = new RegistrationResponse(userExp);
		return response.build("User is Registered", HttpStatus.CREATED, response);
	}
}
