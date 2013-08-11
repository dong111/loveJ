/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.services.SiteConfig.java
 * Class:			SiteConfig
 * Date:			2011-12-5
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.service;


import com.ketayao.pojo.SiteConfig;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.0.0
 * @created 2011-12-5 下午2:45:46 
 */

public interface SiteConfigService {
	/**
	 * 
	 * 更新SiteConfig
	 * @param SiteConfig
	 */
	public void update(SiteConfig siteConfig);
	
	/**
	 * 
	 * 得到SiteConfig
	 * @return
	 */
	public SiteConfig get();
	
	/**
	 * 
	 * 描述
	 * @param siteConfig
	 */
	public void create(SiteConfig siteConfig);
}
