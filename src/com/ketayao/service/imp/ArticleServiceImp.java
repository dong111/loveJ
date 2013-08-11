/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.service.imp.ArticleServiceImp.java
 * Class:			ArticleServiceImp
 * Date:			2012-4-9
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ketayao.dao.ArticleDao;
import com.ketayao.dao.PageInfo;
import com.ketayao.pojo.Article;
import com.ketayao.service.ArticleService;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-9 下午3:17:05 
 */
@Service
public class ArticleServiceImp implements ArticleService {
	@Autowired
	private ArticleDao articleDao;

	/**   
	* @param article  
	* @see com.ketayao.service.ArticleService#create(com.ketayao.pojo.Article)  
	*/
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void create(Article article) {
		articleDao.save(article);
	}

	/**   
	* @param id  
	* @see com.ketayao.service.ArticleService#delete(java.lang.Integer)  
	*/
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(Integer id) {
		articleDao.delete(id);
	}

	/**   
	* @param id
	* @return  
	* @see com.ketayao.service.ArticleService#get(java.lang.Integer)  
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public Article get(Integer id) {
		return articleDao.get(id);
	}

	/**   
	* @param article  
	* @see com.ketayao.service.ArticleService#update(com.ketayao.pojo.Article)  
	*/
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void update(Article article) {
		articleDao.update(article);
	}

	/**   
	* @param categoryId
	* @param pageInfo
	* @return  
	* @see com.ketayao.service.ArticleService#findPage(java.lang.Integer, com.ketayao.dao.PageInfo)  
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public List<Article> findPage(Integer categoryId, PageInfo pageInfo) {
		return articleDao.findPage(categoryId, pageInfo);
	}

	/**   
	* @param pageInfo
	* @param trash
	* @return  
	* @see com.ketayao.service.ArticleService#findPage(com.ketayao.dao.PageInfo, java.lang.Boolean)  
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public List<Article> findPage(PageInfo pageInfo, Boolean trash) {
		return articleDao.findPage(pageInfo, trash);
	}

	/**   
	* @param pageInfo
	* @return  
	* @see com.ketayao.service.ArticleService#findNewest(com.ketayao.dao.PageInfo)  
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public List<Article> findNewest(PageInfo pageInfo) {
		return articleDao.findNewest(pageInfo);
	}

	/**   
	* @param pageInfo
	* @param status
	* @return  
	* @see com.ketayao.service.ArticleService#findPage(com.ketayao.dao.PageInfo, java.lang.String)  
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public List<Article> findPage(PageInfo pageInfo, String status) {
		return articleDao.findPage(pageInfo, status);
	}

	/**   
	* @param pageCategoryIds
	* @param postTime
	* @return  
	* @see com.ketayao.service.ArticleService#findPreAndNext(java.util.List, java.util.Date)  
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public List<Article> findPreAndNext(List<Integer> pageCategoryIds,
			Date postTime) {
		return articleDao.findPreAndNext(pageCategoryIds, postTime);
	}

	/**
	 * 
	* @param pageCategoryIds
	* @param pageInfo
	* @return  
	* @see com.ketayao.service.ArticleService#findExtendPage(java.util.List, com.ketayao.dao.PageInfo)
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public List<Article> findExtendPage(List<Integer> pageCategoryIds,
			PageInfo pageInfo) {
		return articleDao.findExtendPage(pageCategoryIds, pageInfo);
	}
	
	/**
	 * 
	* @param categoryId
	* @param pageInfo
	* @return  
	* @see com.ketayao.service.ArticleService#findExtendCategory(java.lang.Integer, com.ketayao.dao.PageInfo)
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public List<Article> findExtendCategory(Integer categoryId,
			PageInfo pageInfo) {
		return articleDao.findExtendCategory(categoryId, pageInfo);
	}

	/**   
	* @param status
	* @param q
	* @param pageInfo
	* @return  
	* @see com.ketayao.service.ArticleService#search(java.lang.String, java.lang.String, com.ketayao.dao.PageInfo)  
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public List<Article> search(String status, String q, PageInfo pageInfo) {
		return articleDao.search(status, q, pageInfo);
	}

	/**   
	 * @param pageInfo
	 * @return  
	 * @see com.ketayao.service.ArticleService#findHome(com.ketayao.dao.PageInfo)  
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public List<Article> findHome(PageInfo pageInfo) {
		return articleDao.findHome(pageInfo);
	}
	
}
