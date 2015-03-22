package com.slgerkamp.introductory.spring.boot.formandbasicauth.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>{
	
}