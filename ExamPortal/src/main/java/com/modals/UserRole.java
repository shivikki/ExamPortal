package com.modals;


public class UserRole {
	
	private int userRoleId;
	
	private int userId;
	private int roleId;
	public int getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	UserRole(int userRoleId, int userId, int roleId) {
		super();
		this.userRoleId = userRoleId;
		this.userId = userId;
		this.roleId = roleId;
	}
	UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
