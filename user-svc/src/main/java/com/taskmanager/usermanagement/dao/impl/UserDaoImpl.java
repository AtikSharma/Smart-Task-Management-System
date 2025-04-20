package com.taskmanager.usermanagement.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taskmanager.common.model.User;
import com.taskmanager.usermanagement.dao.UserDao;
import com.taskmanager.usermanagement.entity.UserEntity;
import com.taskmanager.usermanagement.mapper.UserEntityMapper;
import com.taskmanager.usermanagement.repository.UserRepository;

@Component
public class UserDaoImpl implements UserDao {

	private UserRepository userRepository;

	private UserEntityMapper userEntityMapper;

	@Autowired
	public UserDaoImpl(UserRepository userRepository, UserEntityMapper userEntityMapper) {
		this.userRepository = userRepository;
		this.userEntityMapper = userEntityMapper;
	}

	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User registerUser(User user) {
		UserEntity userEntity = userEntityMapper.mapTo(user);
		UserEntity savedUserEntity = userRepository.save(userEntity);
		return userEntityMapper.mapFrom(savedUserEntity);
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User deleteUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
