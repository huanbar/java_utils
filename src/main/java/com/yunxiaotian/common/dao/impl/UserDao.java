package com.yunxiaotian.common.dao.impl;

 
import org.springframework.stereotype.Repository;

import com.yunxiaotian.common.dao.IUserDao;
import com.yunxiaotian.common.entity.User;

@Repository
public class UserDao extends Dao<User> implements IUserDao {

	@Override
	public User findUserById(Long id) {
		
		return null;
	}

}