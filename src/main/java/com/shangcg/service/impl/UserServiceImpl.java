package com.shangcg.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangcg.dao.IUserDao;
import com.shangcg.pojo.User;
import com.shangcg.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Resource
	private IUserDao userDao;

	public User getUserById(int userId) {
		
		return userDao.selectByPrimaryKey(userId);
	}

	public User saveUser(User user) {
		
		return null;
	}


}
