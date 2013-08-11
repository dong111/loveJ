/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.dao.ContactDao.java
 * Class:			ContactDao
 * Date:			2012-4-8
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.dao;

import java.util.List;

import com.ketayao.pojo.Contact;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-8 上午10:56:37 
 */

public interface ContactDao extends GenericDao<Contact> {
	/**
	 * 
	 * 根据显示状态和删除状态得到分页数据
	 * @param pageInfo
	 * @param status
	 * @return
	 */
	public List<Contact> findPage(PageInfo pageInfo, String status);
	
	/**
	 * 
	 * 根据删除状态得到分页数据
	 * @param pageInfo
	 * @param trash
	 * @return
	 */
	public List<Contact> findPage(PageInfo pageInfo, Boolean trash);
}
