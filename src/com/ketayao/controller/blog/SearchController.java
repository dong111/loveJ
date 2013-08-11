/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.controller.blog.SearchController.java
 * Class:			SearchController
 * Date:			2012-4-11
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
 * @created 2012-4-11 下午12:14:27 
 */
@Controller
@RequestMapping("/blog")
public class SearchController {
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private LinkService linkService;
	
	@Autowired
	private CommentService commentService;
	
	private static final String READ = "blog/blog-search";
	
	@RequestMapping(value="/search", method={RequestMethod.POST, RequestMethod.GET})
	public String search(Map<String, Object> map, PageInfo pageInfo, String param, String q) throws Exception {
		List<Article> articles = null;
		
		if (param != null && !param.trim().equals("")) {
			q = new String(param.getBytes("iso-8859-1"), "utf-8");
		}
		
		if (q != null && !q.trim().equals("")) {
			articles = articleService.search(Article.Status.PUBLISH, q, pageInfo);
			pageInfo.setUrl("search?param=" + q + "&pageIndex=");
			
			for (Article article : articles) {
				article.setSummary(HtmlUtil.filterHtml(article.getContent(), 250));
				article.setTitle(HtmlUtil.highLight(article.getTitle(), q, HtmlUtil.HIGH_LIGHT_SIMPLE_STYLE));
			}
		}
		
		map.put("q", q);
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
