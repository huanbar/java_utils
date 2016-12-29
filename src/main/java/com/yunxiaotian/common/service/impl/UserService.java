package com.yunxiaotian.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yunxiaotian.common.dao.IDao;
import com.yunxiaotian.common.dao.IUserDao;
import com.yunxiaotian.common.dao.impl.UserDao;
import com.yunxiaotian.common.entity.User;
import com.yunxiaotian.common.service.IUserService;

@Service("userService")
public class UserService extends Manage<User, IDao<User>>implements IUserService {

	private IUserDao dao;

	@Resource(name = "userDao")
	public void setDao(UserDao dao) {
		this.dao = dao;
		super.dao = dao;
	}

	public User findUserById(Long id) {
		return dao.findUserById(id);
	}
}