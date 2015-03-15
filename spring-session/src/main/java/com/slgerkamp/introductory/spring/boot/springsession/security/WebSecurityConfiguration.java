package com.slgerkamp.introductory.spring.boot.springsession.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.slgerkamp.introductory.spring.boot.springsession.domain.account.AccountRepository;
import com.slgerkamp.introductory.spring.boot.springsession.common.exception.UserNotFoundException;


/**
 * 認証処理に関する設定を行う。
 *
 */
@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    AccountRepository accountRepository;

    // 暗号化
    @Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
    
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
    	// 暗号化したパスワードに対して確認を行う
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    UserDetailsService userDetailsService() {
        return (username) -> accountRepository
                .findByUsername(username)
                .map(a -> new User(a.username, a.password, true, true, true, true,
                        AuthorityUtils.createAuthorityList("USER", "write")))
                .orElseThrow(
                        () -> new UserNotFoundException("could not find the user '"
                                + username + "'"));
    }
}