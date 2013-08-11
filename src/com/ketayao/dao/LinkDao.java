/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.dao.LinkDao.java
 * Class:			LinkDao
 * Date:			2012-4-7
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.dao;

import java.util.List;

import com.ketayao.pojo.Link;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-7 上午11:20:05 
 */

public interface LinkDao extends GenericDao<Link> {
	
	/**
	 * 
	 * 根据删除状态得到分页数据
	 * @param pageInfo
	 * @param trash
	 * @return
	 */
	public List<Link> findPage(PageInfo pageInfo, Boolean trash);
	
	public List<Link> findPage(PageInfo pageInfo, Boolean trash, String status);
	
	/**
	 * 
	 * 根据显示状态和删除状态得到分页数据
	 * @param pageInfo
	 * @param status
	 * @return
	 */
	public List<Link> findNewest(PageInfo pageInfo, String status);
}
