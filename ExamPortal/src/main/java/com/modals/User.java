package com.modals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.mapper.MasterMapper;
import com.queryConst.QueryConstant;

@Repository
public class User implements UserDetails {
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private boolean enabled = true; // by default true
	private List<Role> authoritiesList;

	

	public User(int id, String username, String password, String firstName, String lastName, String email, String phone,
			boolean enabled, List<Role> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.enabled = enabled;
		this.authoritiesList = authorities;
	}


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", phone=" + phone + ", enabled=" + enabled
				+ ", authorities=" + authoritiesList + ", jdbcTemplate=" + jdbcTemplate + ", jdbcTemplateNamed="
				+ jdbcTemplateNamed + ", userDao=" + userDao + "]";
	}


	public void setAuthorities(List<Role> roleList) {
		this.authoritiesList = roleList;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateNamed;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateNamed=new  NamedParameterJdbcTemplate(dataSource);
	}
	
	@Autowired
	private UserDaoImpl userDao=new UserDaoImpl();
	
	private static final Logger LOG = LoggerFactory.getLogger(User.class);


	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	List<Authority> roleList = new ArrayList<>();
	Role role = new Role();
	try {

		// find what roles tagged to user
		System.out.println("pass id " + id);
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		List<Map<String, Object>> roleData = jdbcTemplateNamed.queryForList(QueryConstant.FIND_ROLE_TAGGED_TO_USER,
				params);

		// List<Map<String, Object>> roleData =
		// jdbcTemplate.queryForList(QueryConstant.FIND_ROLE_TAGGED_TO_USER, id);
		if (!roleData.isEmpty()) {
			for (Map<String, Object> r : roleData) {
				role = (Role) new MasterMapper().mapRole(r);
				// add role name to authority
				roleList.add(new Authority(role.getRoleName()));
			}

		}

	} catch (Exception e) {
		LOG.error("Exception in getAuthorities() m/d"+ e);
		
	}
	// from userRoles can get authority. check what roles tagged to user
	LOG.info("getAuthorities() returning role of user" + roleList);
	return roleList;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

}
