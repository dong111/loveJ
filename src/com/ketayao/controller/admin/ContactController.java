/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.controller.admin.ContactController.java
 * Class:			ContactController
 * Date:			2012-4-8
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ketayao.dao.PageInfo;
import com.ketayao.pojo.Contact;
import com.ketayao.service.ContactService;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-8 上午11:18:49 
 */
@Controller
@RequestMapping("/admin/contact")
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	private static final String READ = "admin/contact/contact-read";
	//private static final String UPDATE = READ;
	//private static final String DELETE = READ;

	@RequestMapping(value="/readContact", method=RequestMethod.GET)
	public String read(Map<String, Object> map, PageInfo pageInfo) {	
		List<Contact> contacts = contactService.findPage(pageInfo, false);
		pageInfo.setUrl("readContact?pageIndex=");
		map.put("contacts", contacts);
		
		return READ;
	}
	
	@RequestMapping(value="/updateContact", method=RequestMethod.GET)
	public String update(Contact contact, PageInfo pageInfo, Map<String, Object> map) {
		String status = contact.getStatus();
		contact = contactService.get(contact.getId());
		contact.setStatus(status);
		contactService.update(contact);
		
		return read(map, pageInfo);
		//return "redirect:" + UPDATE;
		//new ModelAndView(new RedirectView("xxx.do"),"pageInfo",pageInfo);
	}

	@RequestMapping(value="/deleteContact", method=RequestMethod.GET)
	public String delete(Contact contact, PageInfo pageInfo, Map<String, Object> map) {
		contactService.delete(contact.getId());
		return read(map, pageInfo);
		//return DELETE;
	}	
}
