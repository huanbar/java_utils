package com.yunxiaotian.common.service.impl;

import java.io.Serializable;
import java.util.List;
 
import org.springframework.stereotype.Service;

import com.yunxiaotian.common.Page;
import com.yunxiaotian.common.dao.IDao; 
import com.yunxiaotian.common.service.IManage;

@Service
public class Manage<T, D extends IDao<T>> implements IManage<T> {
	protected D dao;

	public void setDao(D dao) {
		this.dao = dao;
	}

	@Override
	public T findObject(String hql) {
		return dao.findObject(hql);
	}

	@Override
	public T findObject(String hql, Object... objects) {
		return dao.findObject(hql, objects);
	}

	@Override
	public T findObject(Class<T> cls, Serializable id) {
		return dao.findObject(cls, id);
	}

	@Override
	public T findObjectBySql(String sql) {
		return dao.findObjectBySql(sql);
	}

	@Override
	public T findObjectBySql(String sql, Object... objects) {
		return dao.findObjectBySql(sql, objects);
	}

	@Override
	public List<T> findList(String hql) {
		return dao.findList(hql);
	}

	@Override
	public List<T> findList(String hql, Object... objects) {
		return dao.findList(hql, objects);
	}

	@Override
	public List<T> findList(Class<T> cls) {
		return dao.findList(cls);
	}

	@Override
	public List<T> findListBySql(String sql) {
		return dao.findListBySql(sql);
	}

	@Override
	public List<T> findListBySql(String sql, Object... objects) {
		return dao.findListBySql(sql, objects);
	}

	@Override
	public void saveObject(T obj) {
		dao.saveObject(obj);
	}

	@Override
	public void updateObject(T obj) {
		dao.updateObject(obj);
	}

	@Override
	public void saveOrUpdateObject(T obj) {
		dao.saveOrUpdateObject(obj);
	}

	@Override
	public int executeSql(String sql) {
		return dao.executeSql(sql);
	}

	@Override
	public int executeSql(String sql, Object... objects) {
		return dao.executeSql(sql, objects);
	}

	@Override
	public int coutObjects(String hql) {
		return dao.coutObjects(hql);
	}

	@Override
	public int countObjects(String hql, Object... objects) {
		return dao.countObjects(hql, objects);
	}

	@Override
	public Page<T> findPageList(String hql, int page, int rows) {
		return dao.findPageList(hql, page, rows);
	}

	@Override
	public Page<T> findPageList(String hql, int page, int rows, Object... objects) {
		return dao.findPageList(hql, page, rows, objects);
	}

	@Override
	public List queryForList(String statementId, Object object) {
		return dao.queryForList(statementId, object);
	}

}