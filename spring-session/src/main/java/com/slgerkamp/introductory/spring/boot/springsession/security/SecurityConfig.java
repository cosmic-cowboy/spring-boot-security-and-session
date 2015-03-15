package com.slgerkamp.introductory.spring.boot.springsession.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * 認証関連の設定を行う
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	// 認証方法
    	// すべてのリクエストに対してbasic認証を行う
    	http.authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic();
        
    }
}