package com.yny.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yny.entity.User;

@Controller
@RequestMapping("login")
public class UserinfoController {

	@RequestMapping("/res")
	public String hello2(User user,Model mv){
		
		mv.addAttribute("username", user.getUsername());
		
		return "sus";
	}
	
}
