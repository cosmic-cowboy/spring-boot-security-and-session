package com.slgerkamp.introductory.spring.boot.formandbasicauth.domain.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {

	@Id
	private String username;
	@JsonIgnore
	private String encodedPassword;
	
}
