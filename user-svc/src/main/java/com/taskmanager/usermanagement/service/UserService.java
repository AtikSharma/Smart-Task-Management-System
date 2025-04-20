package com.taskmanager.usermanagement.service;

import com.taskmanager.common.model.User;

public interface UserService {

	public User getUserDetails(User user);

	public User userRegistration(User user);

	public User updateUser(User user);

	public User deleteUser(User user);
}
