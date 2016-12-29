package com.yunxiaotian.common.actions;

import java.util.ArrayList; 
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
import com.yunxiaotian.common.entity.User;
import com.yunxiaotian.common.service.IUserService; 

/**
 * 用户管理控制器
 * @Description: 用户增删改查
 * @author zhou
 * @date 2016-12-18 
 *
 */
@Controller
public class UserController {

	@Autowired
	private IUserService userService;

	/**
	 * 用戶列表
	 *  
	 * @Description:
	 * @return Object 返回类型
	 * @author zhou
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object user() { 
		List<User> user = new ArrayList<User>(); 
		return user;
	}
}