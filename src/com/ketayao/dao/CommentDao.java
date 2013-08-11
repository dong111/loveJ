/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.dao.CommentDao.java
 * Class:			CommentDao
 * Date:			2012-4-9
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.dao;

import java.util.List;

import com.ketayao.pojo.Comment;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-9 上午11:34:36 
 */

public interface CommentDao extends GenericDao<Comment> {
	/**
	 * 
	 *  根据article的id，查找Comment
	 * @param articleId
	 * @param pageInfo
	 * @param status
	 * @return
	 */
	public List<Comment> findPage(Integer articleId, PageInfo pageInfo, String status);
	
	public List<Comment> findPage(PageInfo pageInfo);
	
	/********************blog*************************/
	public List<Comment> findNewest(PageInfo pageInfo, String status);
}
