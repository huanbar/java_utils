package com.yunxiaotian.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 【用戶】hibernate实体类
 */
@Entity
@Table(name = "t_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;// 表ID

	@Column(name = "user", nullable = false, length = 100)
	private String user;// 用户名

	@Column(name = "password", nullable = true, length = 32)
	private String password;// 密码

	@Column(name = "email", nullable = true, length = 400)
	private String email;// 邮箱

	@Column(name = "phone", nullable = false, length = 1)
	private String phone;// 手机

	@Column(name = "creat_time", nullable = true)
	private Date creatTime;// 创建时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

}