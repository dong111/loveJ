/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.controller.admin.LinkController.java
 * Class:			LinkController
 * Date:			2012-4-7
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.controller.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ketayao.dao.PageInfo;
import com.ketayao.pojo.Link;
import com.ketayao.service.LinkService;
import com.ketayao.system.Constants;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-7 下午2:47:54 
 */
@Controller
@RequestMapping("/admin/contact")
public class LinkController {
	
	@Autowired
	private LinkService linkService;
	
	private final static String CREATE = "admin/contact/link-create";
	private final static String READ = "admin/contact/link-read";
	private final static String UPDATE = "admin/contact/link-update";
	//private final static String DELETE = "admin/contact/link-read";
	
	@InitBinder
	public void userBirthdayDataBinder(WebDataBinder dataBinder) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(df, true)); 
	}
		
	@RequestMapping(value="/createLink", method=RequestMethod.GET)
	public String create() {
		return CREATE;
	}
	
	@RequestMapping(value="/createLink", method=RequestMethod.POST)
	public String create(Map<String,Object> map, Link link) {
		if (link != null && link.getName() != null) {
			link.setCreateTime(new Date());
			linkService.create(link);
			
			map.put(Constants.OPERATION_SUCCESS, Constants.OPERATION_SUCCESS);
		}
		
		return CREATE;
	}
	
	/**
	 * 
	 * 当参数地址相同时pageInfo不用map.put,但是严谨性,请将所需参数手动加入map
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	@RequestMapping(value="/readLink", method=RequestMethod.GET)
	public String read(Map<String,Object> map, PageInfo pageInfo) {		
		List<Link> links = linkService.findPage(pageInfo, false);
		pageInfo.setUrl("readLink?pageIndex=");
		
		//当参数地址相同时pageInfo不用map.put,但是严谨性,请将所需参数手动加入map
		map.put("pageInfo", pageInfo);
		map.put("links", links);
		return READ;
	}
	
	@RequestMapping(value="/updateLink", method=RequestMethod.GET)
	public String preUpdate(Map<String, Object> map, Integer id, Integer pageIndex) {
		Link link = linkService.get(id);
		map.put("link", link);
		map.put("pageIndex", pageIndex);
		return UPDATE;
	}
	
	@RequestMapping(value="/updateLink", method=RequestMethod.POST)
	public String update(Map<String,Object> map, Link link, Integer pageIndex) {
		linkService.update(link);
		map.put(Constants.OPERATION_SUCCESS, Constants.OPERATION_SUCCESS);
		map.put("link", link);
		map.put("pageIndex", pageIndex);

		return UPDATE;
	}

	@RequestMapping(value="/deleteLink", method=RequestMethod.GET)
	public String delete(Map<String, Object> map, Integer id, Integer pageIndex) {
		linkService.delete(id);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageIndex(pageIndex);
		return read(map, pageInfo);
	}
}
