package com.slgerkamp.introductory.spring.boot.springsession.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * ユーザ認証時に利用する。
 *
 */
@RestController
public class AuthController {
	
    @RequestMapping
    String check(Principal principal) {
        return principal.getName();
    }

	/**
	 * ログアウトをする。
	 * @param session
	 */
	@RequestMapping("/logout")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void logout(HttpSession session){
		// セッションを破棄する
		session.invalidate();
	}
}
