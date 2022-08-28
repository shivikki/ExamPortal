package com.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.mapper.MasterMapper;
import com.modals.Authority;
import com.modals.Role;
import com.modals.User;
import com.queryConst.QueryConstant;

@Service
public class UserSecurityServiceImpl implements UserDetailsService {
	@Autowired
	private UserDao userDao;

	private static final Logger LOG = LoggerFactory.getLogger(User.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateNamed;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// find user by email
		User user = userDao.getUserByEmail(email);
		if (user == null) {
			System.out.println("User not found");
			LOG.error("No user found with this credentials");

		}
		LOG.info("loadUserByUsername md " + user);
		return user;
	}

	
}