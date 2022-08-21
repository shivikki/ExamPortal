package com.service;

import java.util.List;
import java.util.Set;

import com.modals.ResultResponse;
import com.modals.Role;
import com.modals.User;

public interface UserService {

	//create user - 1 user can have multiple roles
	public ResultResponse createUser(User user,List<Role> roleList);
	
	public User getUserByUsername(String username);
	
	public User deleteUserById(int id);
}
