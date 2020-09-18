package com.shangcg.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangcg.dao.IUserDao;
import com.shangcg.pojo.User;
import com.shangcg.service.IUserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
	
	@Resource
	private IUserDao userDao;

	@Override
	public User saveUser(User user) {
		return null;
	}

	public User getUserById(int userId) {
		
		return userDao.selectByPrimaryKey(userId);
	}



	@Override
	public void BatchInsertData() {

		List<User> list = new ArrayList<>();
		User user1 = new User();
		user1.setId(10);
		user1.setUserName("122");
		user1.setAge(5);
		user1.setPassword("222");

		list.add(user1);

		userDao.batchInsertData(list);
//		for (int i = 0; i < 2000000; i++){
//			userDao.batchInsertData(list);
//		}
	}


}
