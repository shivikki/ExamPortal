package com.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modals.ResultResponse;
import com.modals.Role;
import com.modals.User;
import com.service.UserService;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;
	@PostMapping("/addUser")
	public ResultResponse addUser(@RequestBody User user) {
		ResultResponse createUserResonse=new ResultResponse();
		
		//Noemal User Role
		Role role=new Role(102,"Normal");
		List<Role> roleList=new ArrayList<>();
		roleList.add(role);
 		createUserResonse=userService.createUser(user, roleList);
		return createUserResonse;
	}
}
