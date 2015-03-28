package com.slgerkamp.introductory.spring.boot.formandbasicauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.slgerkamp.introductory.spring.boot.formandbasicauth.domain.user.User;
import com.slgerkamp.introductory.spring.boot.formandbasicauth.domain.user.UserRepository;;

/**
 * LOginUserDetailsを作成する
 *
 */
@Service
public class LoginUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		User user = userRepository.findOne(username);
		if(user == null){
			throw new UsernameNotFoundException("The requested user is not found");
		}

		return new LoginUserDetails(user);
	}

}
