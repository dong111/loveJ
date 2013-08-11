/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.service.CategoryService.java
 * Class:			CategoryService
 * Date:			2012-4-9
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.service;

import java.util.List;

import com.ketayao.pojo.Category;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-9 下午12:29:51 
 */

public interface CategoryService {

	/**
	 * 
	 * 创建category
	 * @param category
	 */
	public void create(Category category);
	
	/**
	 * 
	 * 更新category
	 * @param category
	 */
	public void update(Category category);
	
	/**
	 * 
	 * 删除category
	 * @param id
	 */
	public void delete(Integer id);
	
	public Category getCategory(Integer id);
	
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
