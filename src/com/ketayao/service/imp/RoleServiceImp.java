/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.services.imp.RoleServiceImp.java
 * Class:			RoleServiceImp
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

import com.ketayao.dao.RoleDao;
import com.ketayao.pojo.Role;
import com.ketayao.service.RoleService;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2011-12-10 下午2:34:35 
 */
@Service
@Transactional
public class RoleServiceImp implements RoleService {
	@Autowired
	private RoleDao roleDao;
	
	/**   
	 * @param role  
	 * @see com.ketayao.services.RoleService#create(com.ketayao.entities.Role)  
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void create(Role role) {
		roleDao.save(role);
	}

	/**   
	* @return  
	* @see com.ketayao.services.RoleService#findAll()  
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

}
