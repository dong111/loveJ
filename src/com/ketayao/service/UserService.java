/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.services.UserService.java
 * Class:			UserService
 * Date:			2011-11-25
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.service;

import java.util.List;

import com.ketayao.pojo.User;
import com.ketayao.exception.NotFoundUserException;
import com.ketayao.exception.NotMatchUserPasswordException;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.0.0
 * @created 2011-11-25 下午3:07:57 
 */

public interface UserService {
	
	/**
	 * 
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username, String password) throws NotFoundUserException, NotMatchUserPasswordException;
	
	/**
	 * 
	 * 用户更新
	 * @param user
	 */
	public void update(User user);
	
	/**
	 * 创建用户
	 * 描述
	 * @param user
	 */
	public void create(User user);
	
	/**
	 * 
	 * 描述
	 * @return
	 */
	public List<User> findAll();
}
