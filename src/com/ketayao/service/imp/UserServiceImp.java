/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.services.imp.UserServiceImp.java
 * Class:			UserServiceImp
 * Date:			2011-11-25
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.0.0
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

import com.ketayao.dao.UserDao;
import com.ketayao.exception.NotFoundUserException;
import com.ketayao.exception.NotMatchUserPasswordException;
import com.ketayao.pojo.User;
import com.ketayao.service.UserService;
import com.ketayao.util.SimpleEncrypt;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.0.0
 * @created 2011-11-25 下午3:08:26 
 */
@Service("userService")
@Transactional
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	/**   
	 * @param username
	 * @param password
	 * @return
	 * @throws NotFoundUserException
	 * @throws NotMatchUserPasswordException  
	 * @see com.ketayao.services.UserService#login(java.lang.String, java.lang.String)  
	 */
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	@Override
	public User login(String username, String password)
			throws NotFoundUserException, NotMatchUserPasswordException {
		return userDao.login(username, password);
	}

	/**   
	 * @param user  
	 * @see com.ketayao.services.UserService#update(com.ketayao.entities.User)  
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void update(User user) {
		userDao.update(user);
	}

	/**   
	* @param user  
	* @see com.ketayao.services.UserService#create(com.ketayao.entities.User)  
	*/
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void create(User user) {
		user.setPassword(SimpleEncrypt.encrypt(user.getPassword()));
		userDao.save(user);
	}

	/**   
	* @return  
	* @see com.ketayao.services.UserService#findAll()  
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}
}
