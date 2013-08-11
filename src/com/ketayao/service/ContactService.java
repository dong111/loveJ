/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.service.ContactService.java
 * Class:			ContactService
 * Date:			2012-4-8
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.service;

import java.util.List;

import com.ketayao.dao.PageInfo;
import com.ketayao.pojo.Contact;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-8 上午11:12:28 
 */

public interface ContactService {
	/**
	 * 
	 * 得到contact的详细信息
	 * @param id
	 * @return
	 */
	public Contact get(Integer id);
	
	/**
	 * 
	 * 删除Contact
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * 
	 * 创建Contact
	 * @param contact
	 */
	public void create(Contact contact);
	
	/**
	 * 
	 * 更新Contact
	 * @param contact
	 */
	public void update(Contact contact);
	
	/**
	 * 
	 * 描述
	 * @param pageInfo
	 * @param trash
	 * @return
	 */
	public List<Contact> findPage(PageInfo pageInfo, Boolean trash);
}
