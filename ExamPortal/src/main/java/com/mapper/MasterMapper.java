package com.mapper;

import java.util.Map;

import com.modals.User;

public class MasterMapper {

	public User mapUser(Map<String,Object> userMap) {
		User user=new User();
		user.setId((int) userMap.get("id"));
		user.setUsername((String) userMap.get("username"));
		user.setPassword((String) userMap.get("password"));
		user.setFirstName((String) userMap.get("firstName"));
		user.setLastName((String) userMap.get("lastName"));
		user.setEmail((String) userMap.get("email"));
		user.setPhone((String) userMap.get("phone"));
		user.setEnabled((boolean) userMap.get("enabled"));
		
		return user;
	}
}
