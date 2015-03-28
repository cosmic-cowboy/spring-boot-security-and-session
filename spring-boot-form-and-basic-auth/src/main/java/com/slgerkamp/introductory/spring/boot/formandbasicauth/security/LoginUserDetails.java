package com.slgerkamp.introductory.spring.boot.formandbasicauth.security;

import org.springframework.security.core.authority.AuthorityUtils;

import com.slgerkamp.introductory.spring.boot.formandbasicauth.domain.user.User;


/**
 * 認証を行う。
 * Spring SecurityのUserDetailsというデフォルト実装を拡張
 *
 */
public class LoginUserDetails extends org.springframework.security.core.userdetails.User{

	private static final long serialVersionUID = -85189279873379829L;
	
	private final User user;
	
	public LoginUserDetails(User user){
		super(
			user.getUsername(), 
			user.getEncodedPassword(), 
			AuthorityUtils.createAuthorityList("ROLE_USER")
		);
		this.user = user;
	}
}
