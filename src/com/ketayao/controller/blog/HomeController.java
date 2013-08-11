/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.controller.blog.HomeController.java
 * Class:			HomeController
 * Date:			2012-4-10
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.controller.blog;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ketayao.dao.PageInfo;
import com.ketayao.pojo.Article;
import com.ketayao.pojo.Category;
import com.ketayao.pojo.Comment;
import com.ketayao.pojo.Link;
import com.ketayao.service.ArticleService;
import com.ketayao.service.CategoryService;
import com.ketayao.service.CommentService;
import com.ketayao.service.LinkService;
import com.ketayao.util.HtmlUtil;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-10 上午11:50:58 
 */
@Controller
@RequestMapping("/blog")
public class HomeController {
	
	private static final String READ = "blog/home";

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private LinkService linkService;
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String read(Map<String, Object> map) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setUrl("home/");
		return read(map, 1, pageInfo);
	}

	@RequestMapping(value="/home/{pageIndex}", method=RequestMethod.GET)
	public String read(Map<String, Object> map, @PathVariable() Integer pageIndex, PageInfo pageInfo) {
		//PageInfo pageInfo = new PageInfo();
		pageInfo.setPageIndex(pageIndex);
		//pageInfo.setUrl("");
		
		List<Article> articles = articleService.findHome(pageInfo);
		for (Article article : articles) {
			if (article.getSummary() == null || article.getSummary().trim().equals("")) {
				article.setSummary(HtmlUtil.filterHtml(article.getContent(), 500));
			}
		}
		
		map.put("articles", articles);
		
		map.put("pageInfo", pageInfo);
		referenceData(map);
		return READ;
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
