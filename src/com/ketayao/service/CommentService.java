/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.service.CommentService.java
 * Class:			CommentService
 * Date:			2012-4-10
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.service;

import java.util.List;

import com.ketayao.dao.PageInfo;
import com.ketayao.pojo.Comment;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-10 上午10:25:32 
 */

public interface CommentService {
	/**
	 * 
	 * 得到comment的详细信息
	 * @param id
	 * @return
	 */
	public Comment get(Integer id);
	
	/**
	 * 
	 * 删除Comment
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * 
	 * 创建Comment
	 * @param comment
	 */
	public void create(Comment comment);
	
	/**
	 * 
	 * 更新Comment
	 * @param comment
	 */
	public void update(Comment comment);
	
	public List<Comment> findPage(PageInfo pageInfo);
	
	public List<Comment> findPage(Integer articleId, PageInfo pageInfo, String status);
	
	/********************blog*************************/
	public List<Comment> findNewest(PageInfo pageInfo, String status);
}
