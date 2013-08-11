/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ketayao.controller.blog.ViewController.java
 * Class:			ViewController
 * Date:			2012-4-10
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.ketayao.controller.blog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.ketayao.system.Constants;
import com.ketayao.util.SimpleCaptchaServlet;

/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @created 2012-4-10 下午4:56:17 
 */
@Controller
@RequestMapping("/blog")
public class ViewController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private LinkService linkService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private AttachService attachService;
	
	private static final String VIEW = "blog/blog-view";
	
	@RequestMapping(value="/view/{id}", method=RequestMethod.GET)
	public String view(Map<String, Object> map, @PathVariable Integer id) {
		// 初始化所需参数
		Article article = articleService.get(id);
		
		// 判断是否被删除
		if (article.getTrash()) {
			return "404";
		}
		
		List<Category> categories = categoryService.findTree(false);
		
		// pageInfo = new PageInfo();
		List<Integer> idList = new ArrayList<Integer>();
		boolean find = false;

		// 查找所属category=page的id集合（包括category=post）
		for (int i = 0; i < categories.size(); i++) {
			Category c = categories.get(i);
			List<Integer> tmp = new ArrayList<Integer>();

			if (article.getCategoryId().intValue() == c.getId().intValue()) {
				// 组装article的category
				article.setCategory(c);
				tmp.add(c.getId());
				find = true;
			}

			List<Category> children = c.getChildren();

			for (int j = 0; j < children.size(); j++) {
				tmp.add(children.get(j).getId());

				if (article.getCategoryId().intValue() == children.get(j).getId().intValue()) {
					// 组装article的category
					article.setCategory(children.get(j));
					idList.add(c.getId());
					find = true;
				}
			}

			if (find) {
				// category为page的目录
				Category category = c;
				map.put("category", category);
				idList.addAll(tmp);
				break;
			}
		}
		
		if (idList.size() < 1) {
			return "404";
		}

		// 得到当前article的上一篇和下一篇
		List<Article> articles = articleService.findPreAndNext(idList, article.getPostTime());

		// 评论逻辑
		PageInfo commentPage = new PageInfo();
		List<Comment> comments = commentService.findPage(article.getId(), commentPage, Comment.Status.APPROVED);

		// 更新当前article的view浏览次数和评论数
		article.setView(article.getView() + 1);
		articleService.update(article);
		
		List<Attach> attaches = attachService.find(article.getId());

		map.put("categories", categories);
		map.put("article", article);
		map.put("articles", articles);
		map.put("comments", comments);
		map.put("commentPage", commentPage);
		map.put("attaches", attaches);
		referenceData(map);
		return VIEW;
	}
	
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public String reply(HttpServletRequest request, Map<String, Object> map, Comment comment) {
		boolean correct = catchSimpleCaptcha(request);
		if (correct) {
			String ip = request.getRemoteAddr();
			comment.setPostTime(new Date());
			comment.setPostIP(ip);
			
			commentService.create(comment);
			
			//map.put(Constants.OPERATION_SUCCESS, Constants.OPERATION_SUCCESS);
			return "redirect:/blog/view/" + comment.getArticleId();
		} else {
			//addActionError(getText("form.code.wrong"));
			map.put(Constants.OPERATION_FAILURE, Constants.OPERATION_FAILURE);
			map.put("comment", comment);
			return view(map, comment.getArticleId());
		}
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
		List<Link> newestLinks = linkService.findNewest(new PageInfo(), Link.Status.SHOW);
		List<Comment> newestComments = commentService.findNewest(new PageInfo(), Comment.Status.APPROVED);
		List<Article> newestArticles = articleService.findNewest(new PageInfo());
		
		map.put("newestLinks", newestLinks);
		map.put("newestComments", newestComments);
		map.put("newestArticles", newestArticles);
	}
}
