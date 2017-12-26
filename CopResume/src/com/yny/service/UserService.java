package com.yny.service;

import com.yny.entity.User;

public interface UserService extends BaseService<User> {

	public boolean checkUsername(String username) throws Exception;
	
	public void testTrans() throws Exception;
	
}
