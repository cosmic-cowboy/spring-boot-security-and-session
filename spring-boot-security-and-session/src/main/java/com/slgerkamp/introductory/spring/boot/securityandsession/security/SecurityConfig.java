package com.slgerkamp.introductory.spring.boot.securityandsession.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	// 特定のリクエストに対してセキュリティ設定をカスタマイズする
	@Override
	public void configure(WebSecurity web) throws Exception {
		// 静的リソースに対する認証処理を無効にする
		web.ignoring().antMatchers("/webjars/**", "/css/**");
	}
	
	// 認証設定
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// 認可処理、loginFormにはすべてのユーザがアクセスできるようにする
		http.authorizeRequests()
			.antMatchers("/loginForm").permitAll()
			.anyRequest().authenticated();
		
		// ログイン
		// フォーム認証を有効に
		http.formLogin()
			// 認証処理のパス
			.loginProcessingUrl("/login")
			// ログイン・フォーム表示のパス
			.loginPage("/loginForm")
			// 認証失敗時の遷移先
			.failureUrl("/loginForm?error")
			// 認証成功時の遷移先
			.defaultSuccessUrl("/customers", true)
			// ユーザ名、パスワードのパラメータ名
			.usernameParameter("username").passwordParameter("password")
			.and();
		
		// ログアウト
		http.logout()
			// ログアウト処理のパス（文字列のパスを指定した場合はPOSTでアクセスする）
			.logoutRequestMatcher(new AntPathRequestMatcher("logout**"))
			// ログアウト完了後の遷移先
			.logoutSuccessUrl("/loginForm");
	}
	
	/**
	 * 認証に関する設定を行う
	 *
	 */
	static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
		
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
