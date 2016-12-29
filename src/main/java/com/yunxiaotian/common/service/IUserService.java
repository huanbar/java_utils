package com.yunxiaotian.common.service;
 
import com.yunxiaotian.common.entity.User;

public interface IUserService extends IManage<User> {
	public User findUserById(Long id);
}