package com.yunxiaotian.common.dao;

import com.yunxiaotian.common.entity.User;

public interface IUserDao extends IDao<User> {
	public User findUserById(Long id);

}
