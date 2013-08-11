/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.dao.GenericDAO.java
 * Class:			GenericDAO
 * Date:			2012-4-5
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.dao;

import java.util.List;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-5 下午1:41:35 
 */

public interface GenericDao<T> {
	/**
	 * 保存（持久化）一个对象
	 * 
	 * @param entity
	 *            要保存的对象
	 */
	public void save(T entity);

	/**
	 * 更新一个对象
	 * 
	 * @param entity
	 *            要修改的对象
	 */
	public void update(T entity);
	
	/**
	 * 
	 * 描述
	 * @param id
	 */
	public void delete(Integer id);

	/**
	 * 根据类型删除全部对象
	 * 
	 * @param clazz
	 *            类型
	 * @return Integer
	 */
	public Integer deleteAll();

	/**
	 * 
	 * 根据类型和对象id从数据库取得一个对象
	 * @param id
	 * @return
	 */
	public T get(Integer id);


	/**
	 * 查询全部
	 * 
	 * @param clazz
	 *            类型
	 * @return 对象集合
	 */
	public List<T> findAll();
}
