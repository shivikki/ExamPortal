package com.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mapper.MasterMapper;
import com.modals.ResultResponse;
import com.modals.Role;
import com.modals.User;
import com.queryConst.QueryConstant;

@Repository
public class UserDaoImpl implements UserDao {

	private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateNamed;

	// Create new user
	@Override
	public ResultResponse createUser(User user, List<Role> roles) {
		ResultResponse response = new ResultResponse();
		response.setValidationFlag(true); // by default val flag true

		boolean update = true;
		boolean userExist = false;
		boolean roleExist = false;
		User getUser =findUserBEmail(user, response);

		// calling func to check user already exists or not using email( as email is
		// unique)
		System.out.println("getUser obj from val"+getUser);
		try {
			if (getUser == null) {
				int result = 0;

				// create new user if email id does not exist
				result = jdbcTemplate.update(QueryConstant.CREATE_NEW_USER, user.getEmail(), user.getFirstName(),
						user.getLastName(), user.getPassword(), user.getPhone(), user.getUsername(), user.isEnabled());
				System.out.println("result " + result);

				// get newly added user bcoz user is required to insert data in user_role table
				List<Map<String, Object>> userData = jdbcTemplate.queryForList(QueryConstant.FIND_USER_BY_EMAIL,
						user.getEmail());
				System.out.println(userData + "userData created");

				if (!userData.isEmpty()) {
					for (Map<String, Object> u : userData) {
						getUser = (User) new MasterMapper().mapUser(u);
						User storeUser = new User(getUser.getId(), null, null, null, null, null, null, roleExist);
						break;
					}

				}

				if (result > 0) {
					// insert userid and roleid in user_role table
					for (int i = 0; i < roles.size(); i++) {

						System.out.println("uid" + getUser.getId() + " roleid" + roles.get(i).getRoleId());
						int updateUserRole = jdbcTemplate.update(QueryConstant.INSERT_IN_USER_ROLE, getUser.getId(),
								roles.get(i).getRoleId());
						if (updateUserRole > 0) {
							System.out.println("USER ROLE UPDATED");
							response.setValidationFlag(false);
							response.setValidationStatus("User Created");

						}
					}
				}
				else {
					response.setValidationFlag(true);
					response.setValidationStatus("User not created");
				}

			} else {
				response.setValidationFlag(true);
				response.setValidationStatus("User already registered with this email.");
				
			}
		} catch (Exception e) {
			response.setValidationFlag(true);
			response.setValidationStatus("Something went wrong. Try again later !!!");
			LOG.error("Exception in createUser" + e);
		}
		return response;

	}

	// find user by id
	public boolean findUserById(int id) {
		try {
			List<Map<String, Object>> userData = jdbcTemplate.queryForList(QueryConstant.FIND_USER_BY_ID, id);
			System.out.println(userData + "userData");
			if (!userData.isEmpty()) {
				return true; // user exists
			}
		} catch (Exception e) {
			LOG.error("Exception in findUserById" + e);
		}

		// no user exists with this id
		return false;
	}

	// find user exists or not using email
	public User findUserBEmail(User user, ResultResponse response) {
		User getUser = new User();
		try {
			List<Map<String, Object>> userData = jdbcTemplate.queryForList(QueryConstant.FIND_USER_BY_EMAIL,
					user.getEmail());
			System.out.println(userData + "userData");

			if (!userData.isEmpty()) {
				for (Map<String, Object> u : userData) {
					getUser = (User) new MasterMapper().mapUser(u);
				}

			}

		} catch (Exception e) {
			LOG.error("Exception in findUserByEmail" + e);
		}
		// if user already exists with email null value returned
		return getUser;
	}

	// find role exists or not in db
	public boolean findRoleById(int id) {
		try {
			System.out.println("role id" + id);
			List<Map<String, Object>> roleData = jdbcTemplate.queryForList(QueryConstant.FIND_ROLE_BY_ID, id);
			System.out.println(roleData + "roleData");
			if (!roleData.isEmpty()) {
				return true; // role exists
			}
			// no user exists with this id

		} catch (Exception e) {
			LOG.error("Exception in findRoleById" + e);
		}
		return false;
	}

}
