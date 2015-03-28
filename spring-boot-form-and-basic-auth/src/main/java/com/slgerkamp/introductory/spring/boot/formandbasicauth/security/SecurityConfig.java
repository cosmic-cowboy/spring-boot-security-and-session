package com.slgerkamp.introductory.spring.boot.formandbasicauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebMvcSecurity
public class SecurityConfig {

	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
		@Override
		public void configure(WebSecurity web) throws Exception {
			// 静的リソースに対する認証処理を無効にする
			web.ignoring().antMatchers("/webjars/**", "/css/**");
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/api/**")
				.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
				.httpBasic();
		}
	}

		
	/**
	 * 認証に関する設定を行う
	 *
	 */
	@Configuration
	public static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
		
		@Autowired
		UserDetailsService userDetailsService;
		
		@Bean
		PasswordEncoder passwordEncoder(){
			return new BCryptPasswordEncoder();
		}
		
		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		}

	}
}
