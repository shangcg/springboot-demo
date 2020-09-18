package com.shangcg.service;

import com.shangcg.pojo.User;

public interface IUserService {
	
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	public User saveUser(User user);
	
	
	/**
	 * 读取用户
	 * @param userId
	 * @return
	 */
	public User getUserById(int userId);


	void BatchInsertData();
}
