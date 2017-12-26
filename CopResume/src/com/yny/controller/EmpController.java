package com.yny.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yny.entity.Emp;
import com.yny.service.IEmpService;

@Controller
@RequestMapping(value = "/empinfo") 
public class EmpController {
	
	@Resource
	private IEmpService empService;
	
	@RequestMapping(value = "/list",method=RequestMethod.GET) 
	public String getEmpinfoList(Model model){
		
		List<Emp> empinfoList = empService.getEmpInfoList();
		
		model.addAttribute("emp_list", empinfoList);
		
		return "emp_list";
	}

}
