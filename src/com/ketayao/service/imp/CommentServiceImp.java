/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.service.imp.CommentServiceImp.java
 * Class:			CommentServiceImp
 * Date:			2012-4-10
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ketayao.dao.CommentDao;
import com.ketayao.dao.PageInfo;
import com.ketayao.pojo.Comment;
import com.ketayao.service.CommentService;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-10 上午10:27:12 
 */
@Service
public class CommentServiceImp implements CommentService {
	@Autowired
	private CommentDao commentDao;

	/**   
	 * @param id
	 * @return  
	 * @see com.ketayao.service.CommentService#get(java.lang.Integer)  
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public Comment get(Integer id) {
		return commentDao.get(id);
	}

	/**   
	 * @param id  
	 * @see com.ketayao.service.CommentService#delete(java.lang.Integer)  
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(Integer id) {
		commentDao.delete(id);
	}

	/**   
	 * @param comment  
	 * @see com.ketayao.service.CommentService#create(com.ketayao.pojo.Comment)  
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void create(Comment comment) {
		commentDao.save(comment);
	}

	/**   
	 * @param comment  
	 * @see com.ketayao.service.CommentService#update(com.ketayao.pojo.Comment)  
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void update(Comment comment) {
		commentDao.update(comment);
	}

	/**   
	* @param pageInfo
	* @return  
	* @see com.ketayao.service.CommentService#findPage(com.ketayao.dao.PageInfo)  
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public List<Comment> findPage(PageInfo pageInfo) {
		return commentDao.findPage(pageInfo);
	}

	/**
	 * 
	* @param articleId
	* @param pageInfo
	* @param status
	* @return  
	* @see com.ketayao.service.CommentService#findPage(java.lang.Integer, com.ketayao.dao.PageInfo, java.lang.String)
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public List<Comment> findPage(Integer articleId, PageInfo pageInfo,
			String status) {
		return commentDao.findPage(articleId, pageInfo, status);
	}

	/**
	 * 
	* @param pageInfo
	* @param status
	* @return  
	* @see com.ketayao.service.CommentService#findNewest(com.ketayao.dao.PageInfo, java.lang.String)
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public List<Comment> findNewest(PageInfo pageInfo, String status) {
		return commentDao.findNewest(pageInfo, status);
	}

}
