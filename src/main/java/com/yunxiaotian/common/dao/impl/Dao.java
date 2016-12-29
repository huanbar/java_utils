package com.yunxiaotian.common.dao.impl;
/**
 * 
 * @author zhou
 *
 */

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.yunxiaotian.common.Page;
import com.yunxiaotian.common.dao.IDao;

@SuppressWarnings("unchecked")
@Repository("dao")
public class Dao<T> implements IDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	public SqlMapClientTemplate sqlMapClientTemplate;
	@Autowired
	public JdbcTemplate jdbcTemplate;

	Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 
	 * 
	 * #findObject(java.lang.String)
	 */
	@Override
	public T findObject(String hql) {
		List<T> list = findList(hql);
		return (null == list || list.size() == 0) ? null : list.get(0);
	}

	/**
	 * 
	 * 
	 * #findObject(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public T findObject(String hql, Object... objects) {
		List<T> list = findList(hql, objects);
		return (null == list || list.size() == 0) ? null : list.get(0);
	}

	/**
	 * 
	 * 
	 * #findObject(java.lang.Class,
	 * java.io.Serializable)
	 */
	@Override
	public T findObject(Class<T> cls, Serializable id) {
		return (T) getSession().get(cls, id);
	}

	/**
	 * 
	 * 
	 * #findObjectBySql(java.lang.String)
	 */
	@Override
	public T findObjectBySql(String sql) {
		List<T> list = findListBySql(sql);
		return (null == list || list.size() == 0) ? null : list.get(0);
	}

	/**
	 * 
	 * 
	 * #findObjectBySql(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public T findObjectBySql(String sql, Object... objects) {
		List<T> list = findListBySql(sql, objects);
		return (null == list || list.size() == 0) ? null : list.get(0);
	}

	/**
	 * 
	 * 
	 * #findList(java.lang.String)
	 */
	@Override
	public List<T> findList(String hql) {
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	/**
	 * 
	 * 
	 * #findList(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public List<T> findList(String hql, Object... objects) {
		Query query = getSession().createQuery(hql);
		setParameter(query, objects);
		return query.list();
	}

	/**
	 * 
	 * 
	 * #findList(java.lang.Class)
	 */
	@Override
	public List<T> findList(Class<T> cls) {
		String hql = "FROM " + cls.getName();
		return findList(hql);
	}

	/**
	 * 
	 * 
	 * #findListBySql(java.lang.String)
	 */
	@Override
	public List<T> findListBySql(String sql) {
		Query query = getSession().createSQLQuery(sql);
		return query.list();
	}

	/**
	 * 
	 * 
	 * #findListBySql(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public List<T> findListBySql(String sql, Object... objects) {
		Query query = getSession().createSQLQuery(sql);
		setParameter(query, objects);
		return query.list();
	}

	/**
	 * 
	 * 
	 * #saveObject(java.lang.Object)
	 */
	@Override
	public void saveObject(T obj) {
		getSession().save(obj);
	}

	/**
	 * 
	 * 
	 * #updateObject(java.lang.Object)
	 */
	@Override
	public void updateObject(T obj) {
		getSession().update(obj);
	}

	/**
	 * 
	 * 
	 * #saveOrUpdateObject(java.lang.Object)
	 */
	@Override
	public void saveOrUpdateObject(T obj) {
		getSession().saveOrUpdate(obj);
	}

	/**
	 * 
	 * 
	 * #executeSql(java.lang.String)
	 */
	@Override
	public int executeSql(String sql) {
		Query query = getSession().createSQLQuery(sql);
		return query.executeUpdate();
	}

	/**
	 * 
	 * 
	 * #executeSql(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public int executeSql(String sql, Object... objects) {
		Query query = getSession().createSQLQuery(sql);
		setParameter(query, objects);
		return query.executeUpdate();
	}

	/**
	 * 
	 * 
	 * (java.lang.String)
	 */
	@Override
	public int coutObjects(String hql) {
		Query query = getSession().createQuery(hql);
		ScrollableResults sr = query.scroll();
		sr.last();
		return sr.getRowNumber() == -1 ? 0 : sr.getRowNumber() + 1;
	}

	/**
	 * 
	 * 
	 * #countObjects(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public int countObjects(String hql, Object... objects) {
		Query query = getSession().createQuery(hql);
		setParameter(query, objects);
		ScrollableResults sr = query.scroll();
		sr.last();
		return sr.getRowNumber() == -1 ? 0 : sr.getRowNumber() + 1;
	}

	/**
	 * 
	 * 
	 * #findPageList(java.lang.String, int, int)
	 */
	@Override
	public Page<T> findPageList(String hql, int page, int rows) {
		Query query = getSession().createQuery(hql);
		return findPageList(query, page, rows);
	}

	/**
	 * 
	 * 
	 * 
	 * java.lang.Object[])
	 */
	@Override
	public Page<T> findPageList(String hql, int page, int rows, Object... objects) {
		Query query = getSession().createQuery(hql);
		setParameter(query, objects);
		return findPageList(query, page, rows);
	}

	Page<T> findPageList(Query query, int page, int rows) {
		ScrollableResults sr = query.scroll();
		sr.last();
		int count = sr.getRowNumber() == -1 ? 0 : sr.getRowNumber() + 1;
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(page * rows);
		return new Page<T>(page, rows, count, query.list());
	}

	void setParameter(Query query, Object... objects) {
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
	}

	public List queryForList(String statementId, Object object) {
		return this.sqlMapClientTemplate.queryForList(statementId, object);
	}
}