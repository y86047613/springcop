package com.yny.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yny.entity.User;
import com.yny.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements
		UserService {

	@Override
	public boolean checkUsername(String username) throws Exception {

		boolean flag= false;
		String hql="from User where username=?";
		List<User> list = findList(hql,username);
		if(null==list || list.size()==0){
			flag=true;
		
		}
		
		return flag;

		
	}

	@Override
	@Transactional
	public void testTrans() throws Exception {

		User user = new User();
		user.setUsername("usernmae");
		user.setPassword("123456");
		this.save(user);
		User user2 = this.get("from User where adadadad=?", 11);
		user2.setUsername("1111111111111111111111111111");
		int i = 10 / 0;
		this.update(user2);

	}
	
	

}
