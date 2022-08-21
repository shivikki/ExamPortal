package com.dao;

import java.util.List;
import java.util.Set;

import com.modals.ResultResponse;
import com.modals.Role;
import com.modals.User;

public interface UserDao {
	
	public ResultResponse createUser(User user, List<Role> roles);
	
	public User getUserByUsername(String username);
	
	public User deleteUserById(int id);
}
