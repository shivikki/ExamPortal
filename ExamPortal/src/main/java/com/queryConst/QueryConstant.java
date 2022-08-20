package com.queryConst;

public class QueryConstant {

	public static final String FIND_USER_BY_ID="select * from exam.user where id=?";
	
	public static final String FIND_ROLE_BY_ID="select * from exam.role where role_id=?";
	
	public static final String INSERT_IN_USER_ROLE="insert into exam.user_role (user_id,role_id)\r\n"
			+ "values (?,?);";
	
	public static final String CREATE_NEW_USER="INSERT INTO exam.user\r\n"
			+ "(email,first_name,last_name,\r\n"
			+ "password,phone,username,enabled)\r\n"
			+ "VALUES\r\n"
			+ "(?,?,?,?,?,?,?);";
	
	public static final String FIND_USER_BY_EMAIL="select * from exam.user where email=?;";
	
}