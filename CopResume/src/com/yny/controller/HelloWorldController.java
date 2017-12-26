package com.yny.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {

	private String username;
	
	@RequestMapping("/helloworld")
	//@ModelAttribute("yyyyy")
	public String hello(@RequestParam("username") String name,Model model){
		username = name;
		model.addAttribute("username", this.username);
		return "success";
	}
	
	@RequestMapping("/helloworld2")
	public ModelAndView hello2(String username){
		
		ModelAndView mv = new ModelAndView();
		
		this.username = username;
		
		mv.addObject("test", this.username);
		
		mv.setViewName("success"); 
		
		return mv;
	}
	
	@RequestMapping("/helloworld3")
	public ModelAndView hello3(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView();
		
		this.username = request.getParameter("username");
		mv.addObject("test", this.username);
		request.setAttribute("test1","zzx");
		mv.setViewName("success"); 
		
		return mv;
	}
	
	
	@RequestMapping("/helloworld4")
	public String hello4(String username,Map<String,Object> returnMap){
		returnMap.put("test", username);
		returnMap.put("test1", "aaaa");
		returnMap.put("test2", "bbbb");
		return "success";
	}
	
	
}