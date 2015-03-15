package com.slgerkamp.introductory.spring.boot.securityandsession.api;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

	
	/**
	 * @param principal
	 * @return
	 */
	@RequestMapping(value="/",produces = "application/json")
	public Map<String,String> helloUser(Principal principal) {
		HashMap<String,String> result = new HashMap<String,String>();
		result.put("username", principal.getName());
		return result;
	}
	 
	/**
	 * ログアウトをする。
	 * Sessionを
	 * @param session
	 */
	@RequestMapping("/logout")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void logout(HttpSession session){
		// セッションを破棄する
		session.invalidate();
	}
}
