package com.slgerkamp.introductory.spring.boot.formandbasicauth.domain.user;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="users")
public class User {

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEncodedPassword() {
		return encodedPassword;
	}

	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}

	@Id
	private String username;
	@JsonIgnore
	private String encodedPassword;
	
	public User() {
		super();
	}

	public User(String username, String encodedPassword) {
		super();
		this.username = username;
		this.encodedPassword = encodedPassword;
	}

}
