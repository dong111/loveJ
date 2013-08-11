/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.services.imp.UserRoleServiceImp.java
 * Class:			UserRoleServiceImp
 * Date:			2011-12-10
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

import com.ketayao.dao.UserRoleDao;
import com.ketayao.pojo.UserRole;
import com.ketayao.service.UserRoleService;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2011-12-10 下午2:35:53 
 */
@Service
@Transactional
public class UserRoleServiceImp implements UserRoleService {
	@Autowired
	private UserRoleDao userRoleDao;
	
	/**   
	 * @param userRole  
	 * @see com.ketayao.services.UserRoleService#create(com.ketayao.entities.UserRole)  
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void create(UserRole userRole) {
		userRoleDao.save(userRole);
	}

	/**   
	* @return  
	* @see com.ketayao.services.UserRoleService#findAll()  
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	@Override
	public List<UserRole> findAll() {
		return userRoleDao.findAll();
	}

}
