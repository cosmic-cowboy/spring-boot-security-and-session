package com.slgerkamp.introductory.spring.boot.formandbasicauth.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("/admin/loginForm")
    String loginForm() {
        return "loginForm";
    }
    
	@RequestMapping("/admin/index")
	public String chat(){
		return "index";
	}
}
