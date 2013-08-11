/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.service.imp.CategoryService.java
 * Class:			CategoryService
 * Date:			2012-4-9
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

import com.ketayao.dao.CategoryDao;
import com.ketayao.pojo.Category;
import com.ketayao.service.CategoryService;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-9 下午12:34:18 
 */
@Service
public class CategoryServiceImp implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;

	/**   
	* @param category  
	* @see com.ketayao.service.CategoryService#create(com.ketayao.pojo.Category)  
	*/
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void create(Category category) {
		categoryDao.save(category);
	}

	/**   
	* @param category  
	* @see com.ketayao.service.CategoryService#update(com.ketayao.pojo.Category)  
	*/
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void update(Category category) {
		categoryDao.update(category);
	}

	/**   
	* @param id  
	* @see com.ketayao.service.CategoryService#delete(java.lang.Integer)  
	*/
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(Integer id) {
		categoryDao.delete(id);
	}

	/**   
	* @param trash
	* @return  
	* @see com.ketayao.service.CategoryService#findParent(java.lang.Boolean)  
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public List<Category> findParent(Boolean trash) {
		return categoryDao.findParent(trash);
	}

	/**   
	* @param trash
	* @return  
	* @see com.ketayao.service.CategoryService#findTree(java.lang.Boolean)  
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public List<Category> findTree(Boolean trash) {
		return categoryDao.findTree(trash);
	}

	/**   
	* @param id
	* @return  
	* @see com.ketayao.service.CategoryService#getCategory(java.lang.Integer)  
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public Category getCategory(Integer id) {
		return categoryDao.get(id);
	}
}
