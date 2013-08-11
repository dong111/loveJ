/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.dao.UserDao.java
 * Class:			UserDao
 * Date:			2012-4-5
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.dao;

import com.ketayao.pojo.User;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-5 下午3:10:05 
 */

public interface UserDao extends GenericDao<User> {
	/**
	 * 
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username, String password);
}
