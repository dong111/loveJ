/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.controller.admin.SiteConfigController.java
 * Class:			SiteConfigController
 * Date:			2012-4-6
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ketayao.pojo.SiteConfig;
import com.ketayao.service.SiteConfigService;
import com.ketayao.system.Constants;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-6 下午9:50:20 
 */
@Controller
@RequestMapping("/admin/site")
public class SiteConfigController {

	@Autowired
	private SiteConfigService siteConfigService;
	
	@RequestMapping(value="/updateSiteConfig", method=RequestMethod.GET)
	public String update() {
		return "admin/site/siteConfig-read";
	}

	@RequestMapping(value="/updateSiteConfig", method=RequestMethod.POST)
	public String update(HttpServletRequest request, SiteConfig siteConfig) {
		siteConfigService.update(siteConfig);
		request.getSession().getServletContext().setAttribute(Constants.SITE_CONFIG, siteConfig);
		request.setAttribute(Constants.OPERATION_SUCCESS, Constants.OPERATION_SUCCESS);
		return update();
	}
}
