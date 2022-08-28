package com.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.modals.ResultResponse;
import com.modals.Role;
import com.modals.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public ResultResponse createUser(User user, List<Role> roles) {
		return userDao.createUser(user,roles);
	}

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	@Override
	public User deleteUserById(int id) {
		return userDao.deleteUserById(id) ;
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public List<Role> getUserRoles(String email) {
		return userDao.getUserRoles(email);
	}

}
