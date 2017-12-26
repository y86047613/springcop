package com.yny.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yny.entity.Emp;

@Controller
@RequestMapping("/restful")
public class RestfulController {

	@RequestMapping(value="/test1/{aaa}",method=RequestMethod.GET)
	public String test1(@PathVariable("aaa") Integer a,String username){
		System.out.println("aaa==="+a);
		System.out.println("username===="+username);
		return "restful";
	}
	
	@RequestMapping(value="/test1/{aaa}/op/{bbb}",method=RequestMethod.GET)
	public String test2(@PathVariable("aaa") Integer a,@PathVariable("bbb") String b,String username){
		System.out.println("aaa==="+a);
		System.out.println("bbb==="+b);
		System.out.println("username===="+username);
		return "restful";
	}
	
	@RequestMapping(value="/test2/{empno}",method=RequestMethod.PUT)
	public String test3(@PathVariable("empno") Integer eno,Emp emp ){
		System.out.println("emp.empno"+eno);
		System.out.println("emp.ename"+emp.getEname());
		System.out.println("emp.job"+emp.getJob());
		return "restful";
	}
	
	@RequestMapping(value="/test2/{empno}",method=RequestMethod.DELETE)
	public String test4(@PathVariable("empno") Integer eno,Emp emp ){
		System.out.println("emp.empno"+eno);
		System.out.println("emp.ename"+emp.getEname());
		System.out.println("emp.job"+emp.getJob());
		return "restful";
	}
	
}
