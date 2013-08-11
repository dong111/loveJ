/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.service.ArticleService.java
 * Class:			ArticleService
 * Date:			2012-4-9
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.service;

import java.util.Date;
import java.util.List;

import com.ketayao.dao.PageInfo;
import com.ketayao.pojo.Article;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-9 下午12:30:01 
 */

public interface ArticleService {

	/**
	 * 
	 * 创建article
	 * @param article
	 */
	public void create(Article article);
	
	/**
	 * 
	 * 删除article
	 * @param id
	 */
	public void delete(Integer id);
		
	/**
	 * 
	 * 找到article
	 * @param id
	 * @return
	 */
	public Article get(Integer id);
	
	/**
	 * 
	 * 更新article
	 * @param article
	 */
	public void update(Article article);
	
	/**
	 * 
	 * 描述
	 * @param categoryId
	 * @param pageInfo
	 * @return
	 */
	public List<Article> findPage(Integer categoryId, PageInfo pageInfo);
	
	/**
	 * 
	 * 根据删除状态得到分页数据
	 * @param pageInfo
	 * @param trash
	 * @return
	 */
	public List<Article> findPage(PageInfo pageInfo, Boolean trash);
	
	public List<Article> findPage(PageInfo pageInfo, String status);
	
	
	/*********************Blog*******************/
	public List<Article> findHome(PageInfo pageInfo);
	
	public List<Article> findNewest(PageInfo pageInfo);
	
	public List<Article> findPreAndNext(List<Integer> pageCategoryIds, Date postTime);
	
	public List<Article> findExtendPage(List<Integer> pageCategoryIds, PageInfo pageInfo);
	
	public List<Article> findExtendCategory(Integer categoryId, PageInfo pageInfo);
	
	public List<Article> search(String status, String q, PageInfo pageInfo); 
}
