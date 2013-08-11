/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.dao.ArticleDao.java
 * Class:			ArticleDao
 * Date:			2012-4-9
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.dao;

import java.util.Date;
import java.util.List;

import com.ketayao.pojo.Article;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-9 上午11:33:59 
 */

public interface ArticleDao extends GenericDao<Article> {
	/**
	 * 
	 * 根据显示状态和删除状态得到分页数据
	 * @param pageInfo
	 * @param status
	 * @return
	 */
	public List<Article> findPage(PageInfo pageInfo, String status);
	
	/**
	 * 
	 * 根据删除状态得到分页数据
	 * @param pageInfo
	 * @param trash
	 * @return
	 */
	public List<Article> findPage(PageInfo pageInfo, Boolean trash);
	
	/**
	 * 
	 * 描述
	 * @param pageInfo
	 * @param trash
	 * @param status
	 * @return
	 */
	public List<Article> findPage(PageInfo pageInfo, Boolean trash, String status);
	
	/**
	 * 
	 * 描述
	 * @param categoryId
	 * @param pageInfo
	 * @return
	 */
	public List<Article> findPage(Integer categoryId, PageInfo pageInfo);
	
	/***************blog************/
	public List<Article> findHome(PageInfo pageInfo);
	
	public List<Article> findNewest(PageInfo pageInfo);
	
	public List<Article> findPreAndNext(List<Integer> pageCategoryIds, Date postTime);
	
	public List<Article> findExtendPage(List<Integer> pageCategoryIds, PageInfo pageInfo);
	
	public List<Article> findExtendCategory(Integer categoryId, PageInfo pageInfo);
	
	public List<Article> search(String status, String q, PageInfo pageInfo); 
}
