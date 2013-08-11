/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.controller.admin.ArticleController.java
 * Class:			ArticleController
 * Date:			2012-4-9
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

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ketayao.dao.PageInfo;
import com.ketayao.pojo.Article;
import com.ketayao.pojo.Attach;
import com.ketayao.pojo.Category;
import com.ketayao.service.ArticleService;
import com.ketayao.service.AttachService;
import com.ketayao.service.CategoryService;
import com.ketayao.system.Constants;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-9 下午3:20:30 
 */
@Controller
@RequestMapping("/admin/content")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AttachService attachService;
	
	private static final String CREATE = "admin/content/article-create";
	private static final String READ = "admin/content/article-read";
	private static final String UPDATE = "admin/content/article-update";
	private static final String VIEW = "admin/content/article-view";
	
	@InitBinder
	public void userBirthdayDataBinder(WebDataBinder dataBinder) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(df, true)); 
	}
	
	@RequestMapping(value="/topArticle", method=RequestMethod.POST)	
	@ResponseBody 
	public void top(HttpServletRequest request, Boolean top) {
		// 设置置顶
		if (top) {
			request.getSession().setAttribute("top", true);
		} else {
			request.getSession().removeAttribute("top");
		}
	}
	
	@RequestMapping(value="/createArticle", method=RequestMethod.GET)
	public String preCreate(HttpServletRequest request, Map<String, Object> map) {
		// 清除置顶时间
		request.getSession().removeAttribute("top");
		
		List<Category> parents = categoryService.findTree(false);
		map.put("parents", parents);
		return CREATE;
	}

	@RequestMapping(value="/createArticle", method=RequestMethod.POST)
	public String create(HttpServletRequest request, Map<String, Object> map, Article article) {
		Date date = new Date();
		// 更新置顶时间
		Boolean top = (Boolean)request.getSession().getAttribute("top");
		if (top != null && top == true) {
			article.setTopTime(date);
		}
		
		article.setPostTime(date);
		article.setModifyTime(date);
		articleService.create(article);
		//addActionMessage(getText("action.article.add"));
		
		map.put(Constants.OPERATION_SUCCESS, Constants.OPERATION_SUCCESS);
		return preCreate(request, map);
	}
	
	@RequestMapping(value="/readArticle", method=RequestMethod.GET)
	public String read(Map<String, Object> map, PageInfo pageInfo, Integer categoryId) {
		List<Article> articles = null;
		if (categoryId != null) {
			articles = articleService.findPage(categoryId, pageInfo);
			pageInfo.setUrl("readArticle?categoryId=" + categoryId + "&pageIndex=");
		} else {
			articles = articleService.findPage(pageInfo, false);
			pageInfo.setUrl("readArticle?pageIndex=");
		}
		
		List<Category> parents = categoryService.findTree(false);
		
		map.put("pageInfo", pageInfo);
		map.put("articles", articles);
		map.put("parents", parents);
		map.put("categoryId", categoryId);
		
		return READ;
	}
	
	@RequestMapping(value="/updateArticle", method=RequestMethod.GET)
	public String preUpdate(HttpServletRequest request, Map<String, Object> map, Integer id, 
			Integer pageIndex, Integer categoryId) throws Exception {		
		// 清除置顶时间
		request.getSession().removeAttribute("top");
		
		List<Category> parents = categoryService.findTree(false);
		Article article = articleService.get(id);
		
		List<Attach> attaches = attachService.find(article.getId());
		
		map.put("article", article);
		map.put("parents", parents);
		map.put("pageIndex", pageIndex);
		map.put("categoryId", categoryId);
		map.put("attaches", attaches);
		
		return UPDATE;
	}
	
	@RequestMapping(value="/updateArticle", method=RequestMethod.POST)
	public String update(HttpServletRequest request,Map<String, Object> map, 
			Article article, Integer pageIndex, Integer categoryId) {
		Date date = new Date();
		// 更新置顶时间
		Boolean top = (Boolean)request.getSession().getAttribute("top");
		if (top != null && top == true) {
			article.setTopTime(date);
		}
		
		article.setModifyTime(date);
		article.setCategoryId(article.getCategory().getId());
		articleService.update(article);
		
		List<Attach> attaches = attachService.find(article.getId());
		
		List<Category> parents = categoryService.findTree(false);
		//addActionMessage(getText("action.article.update"));
		map.put(Constants.OPERATION_SUCCESS, Constants.OPERATION_SUCCESS);
		map.put("article", article);
		map.put("parents", parents);
		map.put("pageIndex", pageIndex);
		map.put("categoryId", categoryId);
		map.put("attaches", attaches);
		return UPDATE;
	}
	
	@RequestMapping(value="/deleteArticle", method=RequestMethod.GET)
	public String delete(Map<String, Object> map, Integer categoryId, Integer id, PageInfo pageInfo) {
		articleService.delete(id);
		return read(map, pageInfo, categoryId);
	}
	
	@RequestMapping(value="/viewArticle", method=RequestMethod.GET)
	public String view(Map<String, Object> map, Integer id) {
		Article article = articleService.get(id);
		map.put("article", article);
		return VIEW;
	}
}
