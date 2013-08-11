/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.service.LinkService.java
 * Class:			LinkService
 * Date:			2012-4-7
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.service;

import java.util.List;

import com.ketayao.dao.PageInfo;
import com.ketayao.pojo.Link;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-7 下午2:48:18 
 */

public interface LinkService {
	/**
	 * 
	 * 得到link的详细信息
	 * @param id
	 * @return
	 */
	public Link get(Integer id);
	
	/**
	 * 
	 * 删除Link
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * 
	 * 创建Link
	 * @param link
	 */
	public void create(Link link);
	
	/**
	 * 
	 * 更新Link
	 * @param link
	 */
	public void update(Link link);
	
	/**
	 * 
	 * 描述
	 * @param pageInfo
	 * @param trash
	 * @return
	 */
	public List<Link> findPage(PageInfo pageInfo, Boolean trash);
	
	public List<Link> findNewest(PageInfo pageInfo, String status);
}
