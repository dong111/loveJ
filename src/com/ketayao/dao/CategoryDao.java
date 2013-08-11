/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.dao.CategoryDao.java
 * Class:			CategoryDao
 * Date:			2012-4-9
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.dao;

import java.util.List;

import com.ketayao.pojo.Category;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-9 上午11:34:21 
 */

public interface CategoryDao extends GenericDao<Category> {
	
	/**
	 * 
	 * 得到category
	 * @param name
	 * @return
	 */
	public Category getCategory(String name);

	/**
	 * 
	 * 得到所有的type=page的category，他们是type=post的父category，并且patent_id=null
	 * @param trash
	 * @return
	 */
	public List<Category> findParent(Boolean trash);
	
	/**
	 * 
	 * 查找所有category，并以树状结构展示
	 * @return
	 */
	public List<Category> findTree(Boolean trash);
}
