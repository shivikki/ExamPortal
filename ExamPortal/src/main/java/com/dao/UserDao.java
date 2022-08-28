package com.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.modals.ResultResponse;
import com.modals.Role;
import com.modals.User;

public interface UserDao {
	
	public ResultResponse createUser(User user, List<Role> roles);
	
	public User getUserByUsername(String username);
	
	public User deleteUserById(int id);
	
	public User getUserByEmail(String email);
	
	public List<Role> getUserRoles(String email);
}
