package com.slgerkamp.introductory.spring.boot.formandbasicauth.api;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicAuthenticationController {

	
	/**
	 * @param principal
	 * @return
	 */
	@RequestMapping(value="/api/login",produces = "application/json")
	public Map<String,String> auth(Principal principal) {
		HashMap<String,String> result = new HashMap<String,String>();
		result.put("username", principal.getName());
		return result;
	}
	 
	/**
	 * ログアウトをする。
	 * Sessionを
	 * @param session
	 */
	@RequestMapping("/api/logout")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void logout(HttpSession session){
		// セッションを破棄する
		session.invalidate();
	}
}
