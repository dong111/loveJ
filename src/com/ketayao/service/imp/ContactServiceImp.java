/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.service.imp.ContactServiceImp.java
 * Class:			ContactServiceImp
 * Date:			2012-4-8
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

import com.ketayao.dao.ContactDao;
import com.ketayao.dao.PageInfo;
import com.ketayao.pojo.Contact;
import com.ketayao.service.ContactService;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-8 上午11:13:54 
 */
@Service
@Transactional
public class ContactServiceImp implements ContactService {
	@Autowired
	private ContactDao contactDao;

	/**
	 * 
	 * @param id
	 * @return  
	 * @see com.ketayao.service.ContactService#get(java.lang.Integer)
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public Contact get(Integer id) {
		return contactDao.get(id);
	}

	/**   
	 * @param id  
	 * @see com.ketayao.service.ContactService#get(java.lang.Integer)
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(Integer id) {
		contactDao.delete(id);
	}

	/**   
	 * @param contact  
	 * @see com.ketayao.service.ContactService#create(com.ketayao.pojo.Contact)  
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void create(Contact contact) {
		contactDao.save(contact);
	}

	/**   
	 * @param contact  
	 * @see com.ketayao.service.ContactService#update(com.ketayao.pojo.Contact)  
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void update(Contact contact) {
		contactDao.update(contact);
	}

	/**   
	 * @param pageInfo
	 * @param trash
	 * @return  
	 * @see com.ketayao.service.ContactService#findPage(com.ketayao.dao.PageInfo, java.lang.Boolean)  
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public List<Contact> findPage(PageInfo pageInfo, Boolean trash) {
		return contactDao.findPage(pageInfo, trash);
	}

}
