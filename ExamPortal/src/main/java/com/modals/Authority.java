package com.modals;

import org.springframework.security.core.GrantedAuthority;

//create new class for getAuthorities() m/d
public class Authority implements GrantedAuthority{

	private String authority;
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return authority;
	}
	public Authority(String authority) {
		super();
		this.authority = authority;
	}
	

}