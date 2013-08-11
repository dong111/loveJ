/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.service.imp.AttachServiceImp.java
 * Class:			AttachServiceImp
 * Date:			2012-4-15
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

import com.ketayao.dao.AttachDao;
import com.ketayao.pojo.Attach;
import com.ketayao.service.AttachService;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @since   2012-4-15 下午4:06:40 
 */
@Service
public class AttachServiceImp implements AttachService {
	@Autowired
	private AttachDao attachDao;

	/**   
	 * @param attach  
	 * @see com.ketayao.service.AttachService#create(com.ketayao.pojo.Attach)  
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void create(Attach attach) {
		attachDao.save(attach);
	}

	/**   
	 * @param id  
	 * @see com.ketayao.service.AttachService#delete(java.lang.Integer)  
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(Integer id) {
		attachDao.delete(id);
	}

	/**
	 * 
	 * @param id
	 * @return  
	 * @see com.ketayao.service.AttachService#get(java.lang.Integer)
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public Attach get(Integer id) {
		return attachDao.get(id);
	}

	/**   
	 * @param articleId
	 * @return  
	 * @see com.ketayao.service.AttachService#find(java.lang.Integer)  
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public List<Attach> find(Integer articleId) {
		return attachDao.find(articleId);
	}

	/**   
	 * @param attach  
	 * @see com.ketayao.service.AttachService#update(com.ketayao.pojo.Attach)  
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void update(Attach attach) {
		attachDao.update(attach);
	}


}
