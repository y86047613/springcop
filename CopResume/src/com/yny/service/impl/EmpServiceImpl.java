package com.yny.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yny.dao.IEmpDao;
import com.yny.entity.Emp;
import com.yny.service.IEmpService;
@Service
@Transactional
public class EmpServiceImpl implements IEmpService {

	@Resource
	private IEmpDao empDao;
	
	@Override
	public List<Emp> getEmpInfoList() {
		
		List<Emp> empinfoList = empDao.getEmpInfoList();
		
		return empinfoList;
	}

}
