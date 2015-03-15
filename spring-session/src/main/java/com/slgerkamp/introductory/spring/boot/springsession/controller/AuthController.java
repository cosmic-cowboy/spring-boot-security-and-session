package com.slgerkamp.introductory.spring.boot.springsession.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
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
    
}
