/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.controller.blog.ExtendPageController.java
 * Class:			ExtendPageController
 * Date:			2012-4-11
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.controller.blog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ketayao.dao.PageInfo;
import com.ketayao.pojo.Article;
import com.ketayao.pojo.Attach;
import com.ketayao.pojo.Category;
import com.ketayao.pojo.Comment;
import com.ketayao.pojo.Link;
import com.ketayao.service.ArticleService;
import com.ketayao.service.AttachService;
import com.ketayao.service.CategoryService;
import com.ketayao.service.CommentService;
import com.ketayao.service.LinkService;
import com.ketayao.util.HtmlUtil;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-11 上午10:52:58 
 */
@Controller
@RequestMapping("/blog")
public class ExtendPageController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private LinkService linkService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private AttachService attachService;
	
	private static final String READ = "blog/blog-read";
	private static final String PAGE = "blog/blog-page";
	
	@RequestMapping(value="/extendCategory/{categoryId}/{pageIndex}", method=RequestMethod.GET)
	public String extendCategory(Map<String, Object> map, @PathVariable Integer categoryId, @PathVariable Integer pageIndex) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageIndex(pageIndex);
		
		Category category = new Category();
		category.setId(categoryId);
		
		List<Category> categories = categoryService.findTree(false);
		//pageInfo.setUrl("extendCategory/" + category.getId() + "/");
		
		boolean find = false;
		for (int i = 0; i < categories.size(); i++) {
			Category c = categories.get(i);
			if (category.getId().intValue() == c.getId().intValue()) {
				category = c;
				break;
			}
			
			List<Category> children = c.getChildren();
			for (int j = 0; j < children.size(); j++) {
				if (category.getId().intValue() == children.get(j).getId().intValue()) {
					category.setParent(c);
					find = true;
					break;
				}
			}
			
			if (find) {
				break;
			}		
		}
		
		List<Article> articles = articleService.findExtendCategory(category.getId(), pageInfo);
		
		for (Article article : articles) {
			if (article.getSummary() == null || article.getSummary().trim().equals("")) {
				article.setSummary(HtmlUtil.filterHtml(article.getContent(), 500));
			}
		}
		
		map.put("pageInfo", pageInfo);
		map.put("category", category);
		map.put("categories", categories);
		map.put("articles", articles);
		referenceData(map);
		return READ;
	}

	@RequestMapping(value="/extendPage/{categoryId}/{pageIndex}", method=RequestMethod.GET)
	public String extendPage(Map<String, Object> map, @PathVariable Integer categoryId, @PathVariable Integer pageIndex) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageIndex(pageIndex);
		
		Category category = new Category();
		category.setId(categoryId);
		
		List<Category> categories = categoryService.findTree(false);
		List<Integer> idList = new ArrayList<Integer>();
		
		//pageInfo.setUrl("extendPage/" + category.getId() + "/");
		
		List<Article> articles = null;
		for (int i = 0; i < categories.size(); i++) {
			Category c = categories.get(i);
			if (category.getId().intValue() == c.getId().intValue()) {
				category = c;
				List<Category> children = c.getChildren();
				idList.add(c.getId());
				for (int j = 0; j < children.size(); j++) {
					idList.add(children.get(j).getId());
				}
				
				articles = articleService.findExtendPage(idList, pageInfo);
				
				for (Article article : articles) {
					if (article.getSummary() == null || article.getSummary().trim().equals("")) {
						article.setSummary(HtmlUtil.filterHtml(article.getContent(), 500));
					}
				}
				
				// just is a simple page.
				if (articles.size() == 1 && idList.size() == 1) {
					Article article = articles.get(0);
					
					List<Attach> attaches = attachService.find(article.getId());
					
					map.put("category", category);
					map.put("article", article);
					map.put("categories", categories);
					map.put("attaches", attaches);
					referenceData(map);
					return PAGE;
				}
				break;
			}
		}
		
		map.put("pageInfo", pageInfo);
		map.put("category", category);
		map.put("categories", categories);
		map.put("articles", articles);
		referenceData(map);
		return READ;
	}
	
	public void referenceData(Map<String, Object> map) {
		List<Link> newestLinks = linkService.findNewest(new PageInfo(), Link.Status.SHOW);
		List<Comment> newestComments = commentService.findNewest(new PageInfo(), Comment.Status.APPROVED);
		List<Article> newestArticles = articleService.findNewest(new PageInfo());
		
		map.put("newestLinks", newestLinks);
		map.put("newestComments", newestComments);
		map.put("newestArticles", newestArticles);
	}
}
