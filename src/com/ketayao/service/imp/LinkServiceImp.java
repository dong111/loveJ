/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.service.imp.LinkServiceImp.java
 * Class:			LinkServiceImp
 * Date:			2012-4-7
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

import com.ketayao.dao.LinkDao;
import com.ketayao.dao.PageInfo;
import com.ketayao.pojo.Link;
import com.ketayao.service.LinkService;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-7 下午2:50:30 
 */
@Service
@Transactional
public class LinkServiceImp implements LinkService {
	
	@Autowired
	private LinkDao linkDao;
	/**   
	 * @param id
	 * @return  
	 * @see com.ketayao.service.LinkService#get(java.lang.Integer)  
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public Link get(Integer id) {
		return linkDao.get(id);
	}

	/**   
	 * @param id  
	 * @see com.ketayao.service.LinkService#delete(java.lang.Integer)  
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(Integer id) {
		linkDao.delete(id);
	}

	/**   
	 * @param link  
	 * @see com.ketayao.service.LinkService#create(com.ketayao.pojo.Link)  
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void create(Link link) {
		linkDao.save(link);
	}

	/**   
	 * @param link  
	 * @see com.ketayao.service.LinkService#update(com.ketayao.pojo.Link)  
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void update(Link link) {
		linkDao.update(link);
	}

	/**   
	 * @param pageInfo
	 * @param trash
	 * @return  
	 * @see com.ketayao.service.LinkService#findPage(com.ketayao.dao.PageInfo, java.lang.Boolean)  
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public List<Link> findPage(PageInfo pageInfo, Boolean trash) {
		return linkDao.findPage(pageInfo, false);
	}

	/**   
	* @param pageInfo
	* @param status
	* @return  
	* @see com.ketayao.service.LinkService#findNewest(com.ketayao.dao.PageInfo, java.lang.String)  
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public List<Link> findNewest(PageInfo pageInfo, String status) {
		return linkDao.findNewest(pageInfo, status);
	}

}
