package com.yunxiaotian.common.service;

import java.io.Serializable;
import java.util.List;

import com.yunxiaotian.common.Page; 

public interface IManage<T> {
	 /**
     * 
     * @param hql
     * @return
     */
     T findObject(String hql);
     
    /**
     * 
     * @param hql
     * @param objects
     * @return
     */
     T findObject(String hql, Object...objects);
     
    /**
     * 
     * @param cls
     * @param id
     * @return
     */
      T findObject(Class<T> cls, Serializable id);
     
    /**
     * 
     * @param sql
     * @return
     */
     T findObjectBySql(String sql);
     
    /**
     * 
     * @param sql
     * @param objects
     * @return
     */
      T findObjectBySql(String sql, Object...objects);
     
    /**
     * 
     * @param hql
     * @return
     */
      List<T> findList(String hql);
     
    /**
     * 
     * @param hql
     * @param objects
     * @return
     */
    List<T> findList(String hql, Object...objects);
     
    /**
     * 
     * @param cls
     * @return
     */
      List<T> findList(Class<T> cls);
     
    /**
     * 
     * @param sql
     * @return
     */
    List<T> findListBySql(String sql);
     
    /**
     * 
     * @param sql
     * @param objects
     * @return
     */
      List<T> findListBySql(String sql, Object...objects);
     
    /**
     * 
     * @param obj
     */
    void saveObject(T obj);
     
    /**
     * 
     * @param obj
     */
      void updateObject(T obj);
     
    /**
     * 
     * @param obj
     */
      void saveOrUpdateObject(T obj);
     
    /**
     * 
     * @param sql
     * @return
     */
    int executeSql(String sql);
     
    /**
     * 
     * @param sql
     * @param objects
     * @return
     */
    int executeSql(String sql, Object...objects);
     
    /**
     * 
     * @param hql
     * @return
     */
    int coutObjects(String hql);
     
    /**
     * 
     * @param hql
     * @param objects
     * @return
     */
    int countObjects(String hql, Object...objects);
     
    /**
     * 
     * @param hql
     * @param page
     * @param rows
     * @return
     */
      Page<T> findPageList(String hql, int page, int rows);
     
    /**
     * 
     * @param hql
     * @param page
     * @param rows
     * @param objects
     * @return
     */
      Page<T> findPageList(String hql, int page, int rows, Object...objects);
     
      public List queryForList(String statementId, Object object);
}