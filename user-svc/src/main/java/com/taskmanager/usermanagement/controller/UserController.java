package com.taskmanager.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.common.model.ServiceResponse;
import com.taskmanager.common.model.User;
import com.taskmanager.common.model.UserBase;
import com.taskmanager.common.model.request.RegistrationRequest;
import com.taskmanager.usermanagement.mapper.UserBOMapper;
import com.taskmanager.usermanagement.service.UserService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

	private UserService userService;

	private UserBOMapper userBOMapper;

	@Autowired
	public UserController(UserService userService, UserBOMapper userBOMapper) {
		this.userService = userService;
		this.userBOMapper = userBOMapper;
	}

	@PostMapping(path = "/register")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "User Registration API", content = @Content(schema = @Schema(implementation = ServiceResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)) })
	public ResponseEntity<UserBase> registration(@RequestBody RegistrationRequest registrationRequest) {
		User user = userBOMapper.mapFromRegistrationRequest(registrationRequest);
		user = userService.userRegistration(user);
		UserBase userBase = userBOMapper.mapFrom(user);
		return new ServiceResponse().build("User is Registered", HttpStatus.CREATED, userBase);
	}
}
