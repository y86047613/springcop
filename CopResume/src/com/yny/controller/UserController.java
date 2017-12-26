package com.yny.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yny.entity.User;
import com.yny.service.UserService;

@Controller
@RequestMapping("user/login")
public class UserController {

	@Resource
	private UserService userService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		try {
			userService.testTrans();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String regiser(User user, Map<String, Object> returnMap) {

		
		 /* userService.save(user); returnMap.put("registerName",
		  user.getUsername());*/
		 
		return "register_success";
	}

	@RequestMapping(value = "/user_name", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkUsername(User user) throws Exception {

		Map<String, Object> jsonMap = new HashMap<String, Object>();

		boolean flag = userService.checkUsername(user.getUsername());

		jsonMap.put("flag", flag);
		
		if (flag) {
			jsonMap.put("msg", "鎭枩鎮紝璇ュ悕瀛楀彲浠ユ敞鍐岋紒");
		} else {
			jsonMap.put("msg", "瀵逛笉璧凤紝璇ュ悕瀛楀凡缁忚浣跨敤锛�");
		}

		return jsonMap;
	}
	
	
	
	
}
