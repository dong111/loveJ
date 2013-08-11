/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.controller.blog.ContactUsController.java
 * Class:			ContactUsController
 * Date:			2012-4-10
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.controller.blog;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ketayao.dao.PageInfo;
import com.ketayao.pojo.Article;
import com.ketayao.pojo.Category;
import com.ketayao.pojo.Comment;
import com.ketayao.pojo.Contact;
import com.ketayao.pojo.Link;
import com.ketayao.service.ArticleService;
import com.ketayao.service.CategoryService;
import com.ketayao.service.CommentService;
import com.ketayao.service.ContactService;
import com.ketayao.service.LinkService;
import com.ketayao.system.Constants;
import com.ketayao.util.SimpleCaptchaServlet;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-10 下午3:53:11 
 */
@Controller
@RequestMapping("/blog")
public class ContactUsController {

	@Autowired
	private ContactService contactService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private LinkService linkService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ArticleService articleService;
	
	private static final String CREATE = "blog/blog-contact";
	
	@RequestMapping(value="/contact", method=RequestMethod.GET)
	public String preCreate(Map<String, Object> map) {
		referenceData(map);
		return CREATE;
	}
	
	@RequestMapping(value="/contact", method=RequestMethod.POST)
	public String create(HttpServletRequest request, Map<String, Object> map, Contact contact) {
		boolean correct = catchSimpleCaptcha(request);
		if (correct) {
			String ip = request.getRemoteAddr();
			contact.setPostTime(new Date());
			contact.setPostIP(ip);
			
			contactService.create(contact);
			
			//addActionMessage(getText("action.contact.add"));
			map.put(Constants.OPERATION_SUCCESS, Constants.OPERATION_SUCCESS);
			map.put("contact", null);
		} else {
			//addActionError(getText("form.code.wrong"));
			map.put(Constants.OPERATION_FAILURE, Constants.OPERATION_FAILURE);
			map.put("contact", contact);
		}
		
		referenceData(map);
		return CREATE;
	}	
	
	private boolean catchSimpleCaptcha(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String parm = request.getParameter("j_captcha_response");
		String c = (String) session
				.getAttribute(SimpleCaptchaServlet.CAPTCHA_KEY);
		if (!parm.equals(c)) {
			return false;
		} else {
			return true;
		}
	}
	
	public void referenceData(Map<String, Object> map) {
		List<Category> categories = categoryService.findTree(false);
		List<Link> newestLinks = linkService.findNewest(new PageInfo(), Link.Status.SHOW);
		List<Comment> newestComments = commentService.findNewest(new PageInfo(), Comment.Status.APPROVED);
		List<Article> newestArticles = articleService.findNewest(new PageInfo());
		
		map.put("categories", categories);
		map.put("newestLinks", newestLinks);
		map.put("newestComments", newestComments);
		map.put("newestArticles", newestArticles);
	}
}
