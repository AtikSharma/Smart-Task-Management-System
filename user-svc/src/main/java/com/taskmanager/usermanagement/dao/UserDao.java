package com.taskmanager.usermanagement.dao;

import com.taskmanager.common.model.User;

public interface UserDao {

	public User getUser(User user);

	public User registerUser(User user);

	public User updateUser(User user);

	public User deleteUser(User user);
}
