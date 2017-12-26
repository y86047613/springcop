package com.yny.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.yny.dao.IEmpDao;
import com.yny.entity.Emp;

@Repository
public class EmpDaoImpl implements IEmpDao {

	@Resource
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public List<Emp> getEmpInfoList() {
	
		List<Emp> empInfoList = hibernateTemplate.loadAll(Emp.class);
		
		return empInfoList;
	}

}
