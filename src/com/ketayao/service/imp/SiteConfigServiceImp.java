/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.services.imp.SiteConfigServiceImp.java
 * Class:			SiteConfigServiceImp
 * Date:			2011-12-5
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

import com.ketayao.dao.SiteConfigDao;
import com.ketayao.pojo.SiteConfig;
import com.ketayao.service.SiteConfigService;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.0.0
 * @created 2011-12-5 下午2:48:17 
 */
@Service
@Transactional
public class SiteConfigServiceImp implements SiteConfigService {
	@Autowired
	private SiteConfigDao siteConfigDao;

	/**   
	 * @param siteConfig  
	 * @see com.ketayao.services.SiteConfigService#update(com.ketayao.entities.SiteConfig)  
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void update(SiteConfig siteConfig) {
		siteConfigDao.update(siteConfig);
	}

	/**   
	 * @return  
	 * @see com.ketayao.services.SiteConfigService#get()  
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public SiteConfig get() {
		List<SiteConfig> siteConfigs = siteConfigDao.findAll();
		if (siteConfigs != null && siteConfigs.size() > 0) {
			return siteConfigs.get(0);
		}
		return null;
	}

	/**   
	* @param siteConfig  
	* @see com.ketayao.services.SiteConfigService#create(com.ketayao.entities.SiteConfig)  
	*/
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void create(SiteConfig siteConfig) {
		siteConfigDao.save(siteConfig);
	}

}
